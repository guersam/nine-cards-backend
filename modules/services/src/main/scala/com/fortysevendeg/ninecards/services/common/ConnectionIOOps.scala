package com.fortysevendeg.ninecards.services.common

import com.fortysevendeg.ninecards.services.free.algebra.DBResult.DBOps
import com.fortysevendeg.ninecards.services.persistence.PersistenceExceptions.PersistenceException
import doobie.imports.{ ConnectionIO, Transactor }

import scalaz.concurrent.Task
import scalaz.{ -\/, \/- }

object ConnectionIOOps {

  implicit val functorCIO = new cats.Functor[ConnectionIO] {
    override def map[A, B](fa: ConnectionIO[A])(f: (A) ⇒ B): ConnectionIO[B] = fa.map(f)
  }

  implicit class ConnectionIOOps[A](c: ConnectionIO[A]) {
    def liftF[F[_]](implicit dbOps: DBOps[F], transactor: Transactor[Task]): cats.free.Free[F, A] =
      transactor.trans(c).unsafePerformSyncAttempt match {
        case \/-(value) ⇒ dbOps.success(value)
        case -\/(e) ⇒
          dbOps.failure(
            PersistenceException(
              message = "An error was found while accessing to database",
              cause   = Option(e)
            )
          )
      }
  }

  implicit class TaskOps[A](task: Task[A]) {
    def liftF[F[_]](implicit dbOps: DBOps[F]): cats.free.Free[F, A] = task.unsafePerformSyncAttempt match {
      case \/-(value) ⇒ dbOps.success(value)
      case -\/(e) ⇒
        dbOps.failure(
          PersistenceException(
            message = "An error was found while accessing to database",
            cause   = Option(e)
          )
        )
    }
  }

}

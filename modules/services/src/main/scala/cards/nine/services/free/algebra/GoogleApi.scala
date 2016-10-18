package cards.nine.services.free.algebra

import cats.data.Xor
import cats.free.{ Free, Inject }
import cards.nine.domain.account.GoogleIdToken
import cards.nine.services.free.domain.{ TokenInfo, WrongTokenInfo }

object GoogleApi {

  sealed trait Ops[A]

  case class GetTokenInfo(tokenId: GoogleIdToken) extends Ops[WrongTokenInfo Xor TokenInfo]

  class Services[F[_]](implicit I: Inject[Ops, F]) {

    def getTokenInfo(tokenId: GoogleIdToken): Free[F, WrongTokenInfo Xor TokenInfo] =
      Free.inject[Ops, F](GetTokenInfo(tokenId))

  }

  object Services {

    implicit def services[F[_]](implicit I: Inject[Ops, F]): Services[F] =
      new Services

  }

}

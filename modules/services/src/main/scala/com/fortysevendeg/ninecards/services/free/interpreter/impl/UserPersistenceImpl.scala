package com.fortysevendeg.ninecards.services.free.interpreter.impl

import com.fortysevendeg.ninecards.services.free.domain._

class UserPersistenceImpl {

  def addUser(user: User) = user

  def getUserByEmail(email: String) =
    Option(
      User(
        id = Option("32132165"),
        sessionToken = Option("asjdfoaijera")
      )
    )

  def createDevice(userId: Long, androidId: String, deviceToken: Option[String]) =
    Device(
      id = 12345678l,
      userId = userId,
      androidId = androidId,
      deviceToken = deviceToken)

  def updateDevice(userId: Long, androidId: String, deviceToken: Option[String]) =
    Device(
      id = 12345678l,
      userId = userId,
      androidId = androidId,
      deviceToken = deviceToken)

}

object UserPersistenceImpl {

  implicit def userPersistenceImpl = new UserPersistenceImpl()
}

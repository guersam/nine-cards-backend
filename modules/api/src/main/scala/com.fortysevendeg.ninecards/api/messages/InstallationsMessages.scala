package com.fortysevendeg.ninecards.api.messages

object InstallationsMessages {

  case class ApiUpdateInstallationRequest(deviceToken: Option[String])

  case class ApiUpdateInstallationResponse(androidId: String, deviceToken: Option[String])
}

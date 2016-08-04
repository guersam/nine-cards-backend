package com.fortysevendeg.ninecards.services.free.interpreter.googleplay

import com.fortysevendeg.ninecards.services.utils.DummyNineCardsConfig
import org.specs2.mutable.Specification

class ConfigurationSpec extends Specification with DummyNineCardsConfig {

  val playConfiguration = Configuration.configuration

  "GoogleApi Configuration" should {
    "return the info related to the Google API stored in the config file" in {
      playConfiguration.protocol shouldEqual googleplay.protocol
      playConfiguration.host shouldEqual googleplay.host
      playConfiguration.port shouldEqual Option(googleplay.port)
      playConfiguration.resolveOneUri shouldEqual googleplay.uri.resolveOne
      playConfiguration.resolveManyUri shouldEqual googleplay.uri.resolveMany
    }
  }

}
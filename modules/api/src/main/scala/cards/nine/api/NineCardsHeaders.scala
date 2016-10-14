package cards.nine.api

import cards.nine.domain.account.AndroidId
import cards.nine.domain.market.{ MarketToken, Localization }
import org.joda.time.DateTime

object NineCardsHeaders {

  val headerAndroidId = "X-Android-ID"
  val headerGooglePlayToken = "X-Google-Play-Token"
  val headerGoogleAnalyticsToken = "X-Google-Analytics-Token"
  val headerMarketLocalization = "X-Android-Market-Localization"
  val headerSessionToken = "X-Session-Token"
  val headerAuthToken = "X-Auth-Token"
  val headerHerokuForwardedProto = "x-forwarded-proto"

  object Domain {

    case class AuthToken(value: String) extends AnyVal

    case class CurrentDateTime(value: DateTime) extends AnyVal

    case class NewSharedCollectionInfo(currentDate: CurrentDateTime, identifier: PublicIdentifier)

    case class PageNumber(value: Int) extends AnyVal

    case class PageSize(value: Int) extends AnyVal

    case class PublicIdentifier(value: String) extends AnyVal

    case class SessionToken(value: String) extends AnyVal

    case class UserId(value: Long) extends AnyVal

    case class UserContext(userId: UserId, androidId: AndroidId)

    case class GooglePlayContext(
      googlePlayToken: MarketToken,
      marketLocalization: Option[Localization]
    )

  }
}

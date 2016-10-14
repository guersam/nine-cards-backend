package cards.nine.api

import cards.nine.api.NineCardsHeaders._
import cards.nine.api.messages.GooglePlayMessages._
import cards.nine.api.messages.InstallationsMessages.ApiUpdateInstallationRequest
import cards.nine.api.messages.SharedCollectionMessages._
import cards.nine.api.messages.UserMessages.ApiLoginRequest
import cards.nine.domain.application.{ Category, Package }
import cards.nine.processes.ProcessesExceptions.SharedCollectionNotFoundException
import cards.nine.processes.messages.ApplicationMessages.GetAppsInfoResponse
import cards.nine.processes.messages.InstallationsMessages._
import cards.nine.processes.messages.RecommendationsMessages._
import cards.nine.processes.messages.SharedCollectionMessages._
import cards.nine.processes.messages.UserMessages.{ LoginRequest, LoginResponse }
import cards.nine.processes.messages.rankings.GetRankedDeviceApps.RankedDeviceApp
import cards.nine.services.persistence.PersistenceExceptions.PersistenceException
import org.joda.time.{ DateTime, DateTimeZone }
import spray.http.HttpHeaders.RawHeader

object TestData {

  val addedPackages = 5

  val androidId = "f07a13984f6d116a"

  val apiToken = "a7db875d-f11e-4b0c-8d7a-db210fd93e1b"

  val author = "John Doe"

  val authToken = "c8abd539-d912-4eff-8d3c-679307defc71"

  val category = "SOCIAL"

  val community = true

  val deviceToken = Option("d897b6f1-c6a9-42bd-bf42-c787883c7d3e")

  val email = "valid.email@test.com"

  val failingAuthToken = "a439c00e-9a01-4b0e-a446-1d8410229072"

  val googlePlayToken = "8d8f9814-d5cc-4e69-9225-517a5257e5b7"

  val googleAnalyticsToken = "yada-yada-yada"

  val icon = "path-to-icon"

  val installations = 1

  val limit = 20

  val limitPerApp = 25

  val location = Option("US")

  val marketLocalization = "en-us"

  val name = "The best social media apps"

  val now = DateTime.now

  val owned = true

  val packagesName = List(
    "earth.europe.italy",
    "earth.europe.unitedKingdom",
    "earth.europe.germany",
    "earth.europe.france",
    "earth.europe.portugal",
    "earth.europe.spain"
  ).map(Package.apply)

  val deviceApps = Map("countries" → packagesName)

  val excludePackages = packagesName.filter(_.value.length > 18)

  val publicIdentifier = "40daf308-fecf-4228-9262-a712d783cf49"

  val removedPackages = None

  val sessionToken = "1d1afeea-c7ec-45d8-a6f8-825b836f2785"

  val tokenId = "6c7b303e-585e-4fe8-8b6f-586547317331-7f9b12dd-8946-4285-a72a-746e482834dd"

  val userId = 1l

  val views = 1

  object Exceptions {

    val http4sException = org.http4s.InvalidResponseException(msg = "Test error")

    val persistenceException = PersistenceException(
      message = "Test error",
      cause   = None
    )

    val sharedCollectionNotFoundException = SharedCollectionNotFoundException(
      message = "Test error",
      cause   = None
    )
  }

  object Headers {

    val userInfoHeaders = List(
      RawHeader(headerAndroidId, androidId),
      RawHeader(headerSessionToken, sessionToken),
      RawHeader(headerAuthToken, authToken)
    )

    val googlePlayHeaders = List(
      RawHeader(headerGooglePlayToken, googlePlayToken),
      RawHeader(headerMarketLocalization, marketLocalization)
    )

    val failingUserInfoHeaders = List(
      RawHeader(headerAndroidId, androidId),
      RawHeader(headerSessionToken, sessionToken),
      RawHeader(headerAuthToken, failingAuthToken)
    )

    val googleAnalyticsHeaders = List(
      RawHeader(headerGoogleAnalyticsToken, googleAnalyticsToken)
    )
  }

  object Messages {

    val collectionInfo = SharedCollectionUpdateInfo(title = name)

    val packagesStats = PackagesStats(addedPackages, removedPackages)

    val sharedCollection = SharedCollection(
      publicIdentifier = publicIdentifier,
      publishedOn      = now,
      author           = author,
      name             = name,
      installations    = installations,
      views            = views,
      category         = category,
      icon             = icon,
      community        = community,
      owned            = owned,
      packages         = packagesName
    )

    val sharedCollectionInfo = SharedCollectionWithAppsInfo(
      collection = sharedCollection,
      appsInfo   = List.empty
    )

    val apiGetAppsInfoRequest = ApiGetAppsInfoRequest(items = List("", "", "").map(Package.apply))

    val apiGetRecommendationsByCategoryRequest = ApiGetRecommendationsByCategoryRequest(
      excludePackages = excludePackages,
      limit           = limit
    )

    val apiGetRecommendationsForAppsRequest = ApiGetRecommendationsForAppsRequest(
      packages        = packagesName,
      excludePackages = excludePackages,
      limit           = limit,
      limitPerApp     = Option(limitPerApp)
    )

    val apiRankAppsRequest = ApiRankAppsRequest(
      location = location,
      items    = deviceApps
    )

    val getRankedAppsResponse = Map.empty[String, List[RankedDeviceApp]]

    val getRecommendationsByCategoryResponse = GetRecommendationsResponse(Nil)

    val apiCreateCollectionRequest = ApiCreateCollectionRequest(
      author        = author,
      name          = name,
      installations = Option(installations),
      views         = Option(views),
      category      = category,
      icon          = icon,
      community     = community,
      packages      = packagesName
    )

    val apiLoginRequest = ApiLoginRequest(email, androidId, tokenId)

    val apiUpdateCollectionRequest = ApiUpdateCollectionRequest(
      collectionInfo = Option(collectionInfo),
      packages       = Option(packagesName)
    )

    val apiUpdateInstallationRequest = ApiUpdateInstallationRequest(deviceToken)

    val getAppsInfoResponse = GetAppsInfoResponse(Nil, Nil)

    val createOrUpdateCollectionResponse = CreateOrUpdateCollectionResponse(
      publicIdentifier = publicIdentifier,
      packagesStats    = packagesStats
    )

    val getCollectionByPublicIdentifierResponse = GetCollectionByPublicIdentifierResponse(
      data = sharedCollectionInfo
    )

    val getCollectionsResponse = GetCollectionsResponse(Nil)

    val getSubscriptionsByUserResponse = GetSubscriptionsByUserResponse(List(publicIdentifier))

    val loginRequest = LoginRequest(email, androidId, sessionToken, tokenId)

    val loginResponse = LoginResponse(apiToken, sessionToken)

    val subscribeResponse = SubscribeResponse()

    val unsubscribeResponse = UnsubscribeResponse()

    val updateInstallationRequest = UpdateInstallationRequest(userId, androidId, deviceToken)

    val updateInstallationResponse = UpdateInstallationResponse(androidId, deviceToken)

    object rankings {
      import cards.nine.api.messages.{ rankings ⇒ Api }
      import cards.nine.processes.messages.{ rankings ⇒ Proc }
      import cards.nine.services.free.domain.{ rankings ⇒ Domain }

      val ranking = Domain.Ranking(Map(
        Category.SOCIAL → Domain.CategoryRanking(List(Package("testApp")))
      ))
      val getResponse = Proc.Get.Response(ranking)

      val apiRanking = Api.Ranking(List(
        Api.CategoryRanking(Category.SOCIAL, List("socialite", "socialist").map(Package)),
        Api.CategoryRanking(Category.COMMUNICATION, List("es.elpais", "es.elmundo", "uk.theguardian").map(Package.apply))
      ))

      val reloadResponse = Proc.Reload.Response()
      val startDate: DateTime = new DateTime(2016, 7, 15, 0, 0, DateTimeZone.UTC)
      val endDate: DateTime = new DateTime(2016, 8, 21, 0, 0, DateTimeZone.UTC)
      val reloadApiRequest = Api.Reload.Request(startDate, endDate, 5)
      val reloadApiResponse = Api.Reload.Response()

    }
  }

  object Paths {

    val apiDocs = "/apiDocs/index.html"

    val categorize = "/applications/categorize"

    val collections = "/collections"

    val collectionById = "/collections/40daf308-fecf-4228-9262-a712d783cf49"

    val details = "/applications/details"

    val installations = "/installations"

    val invalid = "/chalkyTown"

    val latestCollections = "/collections/latest/SOCIAL/0/25"

    val login = "/login"

    val rankApps = "/applications/rank"

    val recommendationsByCategory = "/recommendations/SOCIAL"

    val recommendationsForApps = "/recommendations"

    val subscriptionByCollectionId = "/collections/subscriptions/40daf308-fecf-4228-9262-a712d783cf49"

    val subscriptionsByUser = "/collections/subscriptions"

    val topCollections = "/collections/top/SOCIAL/0/25"
  }

}

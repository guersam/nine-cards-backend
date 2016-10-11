package cards.nine.processes.messages

object RecommendationsMessages {

  case class GetRecommendationsResponse(items: List[GooglePlayRecommendation])

  case class GooglePlayRecommendation(
    packageName: String,
    title: String,
    free: Boolean,
    icon: String,
    stars: Double,
    downloads: String,
    screenshots: List[String]
  )

  case class RecommendationAppList(apps: List[GooglePlayRecommendation])

  type SearchAppsResponse = RecommendationAppList

}

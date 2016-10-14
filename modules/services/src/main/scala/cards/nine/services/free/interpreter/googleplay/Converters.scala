package cards.nine.services.free.interpreter.googleplay

import cards.nine.domain.application.{ Category, Package }
import cards.nine.googleplay.domain._
import cards.nine.googleplay.processes.{ getcard, ResolveMany }
import cards.nine.services.free.domain.GooglePlay.{ RecommendByCategoryRequest ⇒ _, _ }
import cats.instances.list._
import cats.syntax.monadCombine._

object Converters {

  def toRecommendations(cardsList: FullCardList): Recommendations =
    Recommendations(
      cardsList.cards map toRecommendation
    )

  def toRecommendation(card: FullCard): Recommendation =
    Recommendation(
      packageName = card.packageName,
      title       = card.title,
      free        = card.free,
      icon        = card.icon,
      stars       = card.stars,
      downloads   = card.downloads,
      screenshots = card.screenshots
    )

  def toSearchAppsRequest(
    query: String,
    excludePackages: List[Package],
    limit: Int
  ): SearchAppsRequest = SearchAppsRequest(query, excludePackages, limit)

  def toRecommendByAppsRequest(
    packages: List[Package],
    limitByApp: Int,
    excludedPackages: List[Package],
    limit: Int
  ): RecommendByAppsRequest =
    RecommendByAppsRequest(
      packages,
      limitByApp,
      excludedPackages,
      limit
    )

  def toRecommendByCategoryRequest(
    category: String,
    filter: String,
    excludedPackages: List[Package],
    limit: Int
  ): RecommendByCategoryRequest =
    RecommendByCategoryRequest(
      Category.withName(category),
      PriceFilter.withName(filter),
      excludedPackages,
      limit
    )

  def toAppsInfo(response: ResolveMany.Response): AppsInfo =
    AppsInfo(
      response.pending ++ response.notFound,
      response.apps map toAppInfo
    )

  def toAppsInfo(response: List[getcard.Response]): AppsInfo = {
    val (errors, resolved) = response.separate

    AppsInfo(
      errors map (_.packageName),
      resolved map toAppInfo
    )
  }

  def toAppInfo(card: FullCard): AppInfo =
    AppInfo(
      packageName = card.packageName,
      title       = card.title,
      free        = card.free,
      icon        = card.icon,
      stars       = card.stars,
      downloads   = card.downloads,
      categories  = card.categories
    )
}

package networking

import arrow.core.Either
import model.NetworkError
import model.rss.RssFeed
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class FeedFetcher(
    private val httpClient: HttpClient,
) {
    suspend operator fun invoke(url: String): Either<NetworkError, RssFeed> = safeRssFeedFetch {
        httpClient.get(url).bodyAsText()
    }.map { it.setOriginLink(url) }

    private fun RssFeed.setOriginLink(url: String) =
        copy(subscriptionInfo = subscriptionInfo.copy(link = url))
}

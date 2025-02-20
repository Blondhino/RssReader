package networking

import arrow.core.Either
import arrow.core.raise.either
import model.NetworkError
import model.rss.RssFeed
import model.rss.RssString
import model.rss.RssString.Companion.feed
import kotlinx.coroutines.CancellationException

suspend fun safeRssFeedFetch(
    apiRequest: suspend () -> String,
): Either<NetworkError, RssFeed> = either {
    Either.catch {
        RssString(apiRequest()).mapLeft { NetworkError.SerializationError }.bind().feed()
    }.mapLeft {
        if (it is CancellationException) throw it
        NetworkError.UnknownNetworkError(it.message.orEmpty())
    }.bind()
}

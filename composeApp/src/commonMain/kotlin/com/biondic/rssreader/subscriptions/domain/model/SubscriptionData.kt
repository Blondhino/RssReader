package com.biondic.rssreader.subscriptions.domain.model

import arrow.core.Either
import com.biondic.rssreader.core.model.DatabaseError
import com.biondic.rssreader.core.model.NetworkError

sealed interface SubscriptionData {
    data class Local(val results: Either<DatabaseError, List<Subscription>>) : SubscriptionData
    data class Remote(val results: List<Either<NetworkError, Subscription>>) : SubscriptionData
}

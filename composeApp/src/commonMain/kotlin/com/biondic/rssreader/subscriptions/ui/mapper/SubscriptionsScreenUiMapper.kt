package com.biondic.rssreader.subscriptions.ui.mapper

import arrow.core.Either
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.ui.state.HeaderState
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState

class SubscriptionsScreenUiMapper {
    fun map(
        subscriptions: Either<RepositoryError, List<Subscription>>,
        headerState: HeaderState,
        isRefreshing: Boolean,
    ): SubscriptionsScreenState = when (subscriptions) {
        is Either.Left -> SubscriptionsScreenState.Error
        is Either.Right -> SubscriptionsScreenState.Content(
            subscriptions = subscriptions.value.map { it.toUiItem() },
            isRefreshing = isRefreshing,
            headerState = headerState,
        )
    }
}

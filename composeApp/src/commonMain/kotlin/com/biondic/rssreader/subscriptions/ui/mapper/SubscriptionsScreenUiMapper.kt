package com.biondic.rssreader.subscriptions.ui.mapper

import arrow.core.Either
import com.biondic.rssreader.core.model.RepositoryError
import com.biondic.rssreader.subscriptions.domain.model.Subscription
import com.biondic.rssreader.subscriptions.ui.state.HeaderState
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState
import com.biondic.rssreader.subscriptions.ui.state.TabState

class SubscriptionsScreenUiMapper {
    fun map(
        subscriptions: Either<RepositoryError, List<Subscription>>,
        headerState: HeaderState,
        tabState: TabState,
        isRefreshing: Boolean,
    ): SubscriptionsScreenState = when (subscriptions) {
        is Either.Left -> SubscriptionsScreenState.Error
        is Either.Right -> SubscriptionsScreenState.Content(
            subscriptions = subscriptions.value.map { it.toUiItem() },
            isRefreshing = isRefreshing,
            headerState = headerState,
            tabState = tabState,
            favorites = subscriptions.value.map { it.toUiItem() }.filter { it.isFavorite },
        )
    }
}

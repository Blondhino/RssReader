package com.biondic.rssreader.subscriptions.ui.state

import com.biondic.rssreader.ui.components.SubscriptionScreenTab
import com.biondic.rssreader.subscriptions.ui.model.UISubscriptionItem

sealed interface SubscriptionsScreenState {
    data object Loading : SubscriptionsScreenState
    data object Error : SubscriptionsScreenState
    data class Content(
        val subscriptions: List<UISubscriptionItem>,
        val favorites: List<UISubscriptionItem>,
        val headerState: HeaderState,
        val tabState: TabState,
        val isRefreshing: Boolean,
    ) : SubscriptionsScreenState
}

data class HeaderState(
    val isLoading: Boolean,
    val error: String?,
    val text: String,
    val staticData: HeaderStaticData,
)

data class HeaderStaticData(
    val addButtonText: String,
    val hint: String,
)

data class TabState(
    val currentTab: SubscriptionScreenTab,
    val tabs: List<SubscriptionScreenTab>,
    val favoritesTabTitle: String,
)

package com.biondic.rssreader.subscriptions.ui.mapper

import com.biondic.rssreader.core.ui.components.SubscriptionScreenTab
import com.biondic.rssreader.core.ui.components.SubscriptionScreenTab.Favorites
import com.biondic.rssreader.core.ui.components.SubscriptionScreenTab.Subscriptions
import com.biondic.rssreader.core.utils.Dictionary
import com.biondic.rssreader.subscriptions.ui.state.TabState
import rssreader.composeapp.generated.resources.Res
import rssreader.composeapp.generated.resources.subscriptions_screen_tab_all
import rssreader.composeapp.generated.resources.subscriptions_screen_tab_favorites
import rssreader.composeapp.generated.resources.subscriptions_screen_tab_favorites_title

class SubscriptionsTabsUiMapper(
    private val dictionary: Dictionary,
) {
    private val tabs: List<SubscriptionScreenTab> = listOf(
        Subscriptions(
            tabTitle = dictionary.getString(Res.string.subscriptions_screen_tab_all),
            tabId = 0,
        ),
        Favorites(
            tabTitle = dictionary.getString(Res.string.subscriptions_screen_tab_favorites),
            tabId = 1,
        ),
    )

    fun map(tabId: Int = 0) = TabState(
        currentTab = tabs.first { it.id == tabId },
        tabs = tabs,
        favoritesTabTitle = dictionary.getString(Res.string.subscriptions_screen_tab_favorites_title),
    )
}

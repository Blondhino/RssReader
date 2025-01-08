package com.biondic.rssreader.subscriptions

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.biondic.rssreader.core.networking.FeedFetcher
import kotlinx.coroutines.launch

class SubscriptionsViewModel(
    val feedFetcher: FeedFetcher,
) : ScreenModel {
    init {
        screenModelScope.launch {
            val feedFetchResult = feedFetcher(url = "https://www.france24.com/en/rss")
            println(feedFetchResult)
        }
    }

    fun getScreenTitle() = "Subscriptions Screen"
}

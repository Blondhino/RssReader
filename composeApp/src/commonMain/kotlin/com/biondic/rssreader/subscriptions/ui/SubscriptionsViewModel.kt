package com.biondic.rssreader.subscriptions.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.biondic.rssreader.subscriptions.domain.repo.SubscriptionRepository
import com.biondic.rssreader.subscriptions.domain.usecase.AddNewSubscription
import com.biondic.rssreader.subscriptions.domain.usecase.GetMySubscriptions
import kotlinx.coroutines.launch

class SubscriptionsViewModel(
    private val addNewSubscription: AddNewSubscription,
    private val getMySubscriptions: GetMySubscriptions,
    private val deleteSubscription: SubscriptionRepository,
) : ScreenModel {
    init {
        screenModelScope.launch {
            println("adding")
            println(addNewSubscription("https://www.france24.com/en/rss"))
            println("getting")
            getMySubscriptions(shouldRefresh = true).collect { println(it) }
            println("deleting")
            println(deleteSubscription.deleteSubscription("https://www.france24.com/en/rss"))
            println("getting")
            getMySubscriptions(shouldRefresh = true).collect { println(it) }
        }
    }

    fun getScreenTitle() = "Subscriptions Screen"
}

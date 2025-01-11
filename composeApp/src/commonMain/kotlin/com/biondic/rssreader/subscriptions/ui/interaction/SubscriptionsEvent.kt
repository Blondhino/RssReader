package com.biondic.rssreader.subscriptions.ui.interaction

import com.biondic.rssreader.subscriptions.ui.model.UISubscriptionItem

sealed interface SubscriptionsEvent {
    data class UrlFieldChanged(val url: String) : SubscriptionsEvent
    data class AddButtonClicked(val url: String) : SubscriptionsEvent
    data class RemoveButtonClicked(val url: String) : SubscriptionsEvent
    data object RefreshCalled : SubscriptionsEvent
    data class FavoriteToggleClicked(val subscription: UISubscriptionItem) : SubscriptionsEvent
    data class SubscriptionClick(val subscription: UISubscriptionItem) : SubscriptionsEvent
}

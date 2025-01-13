package com.biondic.rssreader.subscriptions.domain.usecase

data class SubscriptionsCrudOperations(
    val getMySubscriptions: GetMySubscriptions,
    val addNewSubscription: AddNewSubscription,
    val toggleSubscriptionIsFavoriteState: ToggleSubscriptionIsFavoriteState,
    val deleteSubscription: DeleteSubscription,
)

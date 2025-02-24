package com.biondic.rssreader.subscriptions.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.RefreshableLazyColumn
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RefreshCalled
import com.biondic.rssreader.subscriptions.ui.model.UISubscriptionItem

@Composable
fun SubscriptionsList(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onEvent: (SubscriptionsEvent) -> Unit,
    subscriptions: List<UISubscriptionItem>,
) {
    RefreshableLazyColumn(
        modifier = modifier.fillMaxSize().padding(top = 8.dp),
        refreshing = isRefreshing,
        onRefresh = { onEvent(RefreshCalled) },
    ) {
        items(subscriptions, key = { it.url }) {
            UISubscriptionComposable(
                modifier = Modifier.animateItem(),
                subscription = it,
                onEvent = onEvent,
            )
        }
    }
}

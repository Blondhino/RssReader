package com.biondic.rssreader.subscriptions.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biondic.rssreader.core.ui.components.SubmittableText
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.AddButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.UrlFieldChanged
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState.Content

@Composable
fun AllSubscriptions(
    modifier: Modifier = Modifier,
    uiState: Content,
    onEvent: (SubscriptionsEvent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
    ) {
        SubmittableText(
            text = uiState.headerState.text,
            errorText = uiState.headerState.error,
            onTextChange = { onEvent(UrlFieldChanged(it)) },
            hint = uiState.headerState.staticData.hint,
            isLoading = uiState.headerState.isLoading,
            onTrailingIconClick = { onEvent(AddButtonClicked(it)) },
        )
        SubscriptionsList(
            isRefreshing = uiState.isRefreshing,
            onEvent = onEvent,
            subscriptions = uiState.subscriptions,
        )
    }
}

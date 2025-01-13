package com.biondic.rssreader.subscriptions.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState.Content

@Composable
fun FavoriteSubscriptions(
    modifier: Modifier = Modifier,
    uiState: Content,
    onEvent: (SubscriptionsEvent) -> Unit,
) {
    Column {
        Text(
            text = uiState.tabState.favoritesTabTitle,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.secondary,
        )
        SubscriptionsList(
            modifier = modifier.padding(8.dp),
            isRefreshing = uiState.isRefreshing,
            onEvent = onEvent,
            subscriptions = uiState.favorites,
        )
    }
}

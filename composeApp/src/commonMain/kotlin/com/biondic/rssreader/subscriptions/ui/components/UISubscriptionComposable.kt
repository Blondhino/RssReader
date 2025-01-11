package com.biondic.rssreader.subscriptions.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biondic.rssreader.core.ui.components.InfoCard
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.FavoriteToggleClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RemoveButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.SubscriptionClick
import com.biondic.rssreader.subscriptions.ui.model.UISubscriptionItem

@Composable
fun UISubscriptionComposable(
    modifier: Modifier = Modifier,
    onEvent: (SubscriptionsEvent) -> Unit,
    subscription: UISubscriptionItem,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 100.dp)
            .padding(vertical = 4.dp)
            .clickable { onEvent(SubscriptionClick(subscription)) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        PublisherImage(
            modifier = Modifier.size(60.dp),
            imageUrl = subscription.imageUrl,
            title = subscription.title,
        )
        InfoCard(
            modifier = Modifier.fillMaxWidth(0.7f),
            title = subscription.title,
            description = subscription.description,
        )
        SubscriptionOptions(
            modifier = Modifier.fillMaxWidth(),
            onDeleteClick = { onEvent(RemoveButtonClicked(subscription.url)) },
            onFavoriteToggleClick = { onEvent(FavoriteToggleClicked(subscription)) },
            isFavorite = subscription.isFavorite,
        )
    }
}

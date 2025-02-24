package com.biondic.rssreader.subscriptions.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.clickable

@Composable
fun SubscriptionOptions(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onFavoriteToggleClick: () -> Unit,
    isFavorite: Boolean,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        Icon(
            modifier = Modifier.padding(8.dp)
                .clickable(rippleEffectVisible = false, onClick = onFavoriteToggleClick),
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            tint = if (isFavorite) Color.Red else Color.Gray,
        )
        Icon(
            modifier = Modifier.padding(8.dp)
                .clickable(rippleEffectVisible = false, onClick = onDeleteClick),
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.DarkGray,
        )
    }
}

package com.biondic.rssreader.core.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.biondic.rssreader.subscriptions.SubscriptionsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContainer() {
    MaterialTheme {
        Navigator(SubscriptionsScreen())
    }
}

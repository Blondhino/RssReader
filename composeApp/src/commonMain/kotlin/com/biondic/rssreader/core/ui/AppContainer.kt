package com.biondic.rssreader.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.biondic.rssreader.subscriptions.ui.SubscriptionsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppContainer(modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = modifier) {
            Navigator(SubscriptionsScreen())
        }
    }
}

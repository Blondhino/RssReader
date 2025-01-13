package com.biondic.rssreader.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <Tab : ScreenTab> TabSelector(
    modifier: Modifier = Modifier,
    currentTabId: Int,
    tabs: List<Tab>,
    onTabSelected: (Tab) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SecondaryTabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = currentTabId,
        ) {
            tabs.forEach { tab ->
                Tab(
                    selected = tab.id == currentTabId,
                    onClick = { onTabSelected(tab) },
                    text = { Text(text = tab.title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                )
            }
        }
    }
}

sealed class ScreenTab(val title: String, val id: Int)

sealed class SubscriptionScreenTab(
    private val screenTabTitle: String,
    private val screenTabId: Int,
) :
    ScreenTab(screenTabTitle, screenTabId) {
    data class Subscriptions(val tabTitle: String, val tabId: Int) :
        SubscriptionScreenTab(tabTitle, tabId)

    data class Favorites(val tabTitle: String, val tabId: Int) :
        SubscriptionScreenTab(tabTitle, tabId)
}

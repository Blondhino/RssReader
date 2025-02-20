package com.biondic.rssreader.subscriptions.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.biondic.rssreader.article.ui.ArticleScreen
import com.biondic.rssreader.ui.components.LoadingIndicator
import com.biondic.rssreader.ui.components.RetryScreen
import com.biondic.rssreader.ui.components.TabSelector
import com.biondic.rssreader.subscriptions.ui.components.AllSubscriptions
import com.biondic.rssreader.subscriptions.ui.components.FavoriteSubscriptions
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RefreshCalled
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.TabSelected
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsViewEffect.OpenArticles
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState.Content
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState.Error
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState.Loading

class SubscriptionsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: SubscriptionsViewModel = koinScreenModel()
        val navigator = LocalNavigator.currentOrThrow
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.viewEffect.collect { effect ->
                when (effect) {
                    is OpenArticles -> navigator.push(ArticleScreen(effect.url, effect.sourceTitle))
                }
            }
        }

        when (val state = uiState) {
            is Content -> SubscriptionsScreenContent(uiState = state, onEvent = viewModel::onEvent)
            is Error -> RetryScreen { viewModel.onEvent(RefreshCalled) }
            is Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun SubscriptionsScreenContent(
    modifier: Modifier = Modifier,
    uiState: Content,
    onEvent: (SubscriptionsEvent) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { uiState.tabState.tabs.size })
    LaunchedEffect(uiState.tabState.currentTab) {
        pagerState.animateScrollToPage(uiState.tabState.currentTab.id)
    }
    LaunchedEffect(pagerState.currentPage) {
        onEvent(TabSelected(pagerState.currentPage))
    }

    Column {
        TabSelector(
            modifier = Modifier.fillMaxWidth(),
            currentTabId = pagerState.currentPage,
            tabs = uiState.tabState.tabs,
            onTabSelected = { onEvent(TabSelected(it.id)) },
        )

        HorizontalPager(
            state = pagerState,
        ) { page ->
            when (page) {
                1 -> FavoriteSubscriptions(
                    modifier = modifier,
                    uiState = uiState,
                    onEvent = onEvent,
                )

                0 -> AllSubscriptions(
                    modifier = modifier,
                    uiState = uiState,
                    onEvent = onEvent,
                )
            }
        }
    }
}

package com.biondic.rssreader.subscriptions.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.biondic.rssreader.article.ui.ArticleScreen
import com.biondic.rssreader.core.ui.components.LoadingIndicator
import com.biondic.rssreader.core.ui.components.RefreshableLazyColumn
import com.biondic.rssreader.core.ui.components.RetryScreen
import com.biondic.rssreader.core.ui.components.SubmittableText
import com.biondic.rssreader.subscriptions.ui.components.UISubscriptionComposable
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.AddButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RefreshCalled
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.UrlFieldChanged
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
        RefreshableLazyColumn(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            refreshing = uiState.isRefreshing,
            onRefresh = { onEvent(RefreshCalled) },
        ) {
            items(uiState.subscriptions, key = { it.url }) {
                UISubscriptionComposable(
                    modifier = Modifier.animateItem(),
                    subscription = it,
                    onEvent = onEvent,
                )
            }
        }
    }
}

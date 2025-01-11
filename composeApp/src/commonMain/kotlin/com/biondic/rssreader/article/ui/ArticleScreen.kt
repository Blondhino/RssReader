package com.biondic.rssreader.article.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.biondic.rssreader.article.ui.components.UIArticleComposable
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent.ArticleClick
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent.RefreshCalled
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent.TopBarBackClick
import com.biondic.rssreader.article.ui.interaction.ArticlesViewEffect.GoBackToSubscriptions
import com.biondic.rssreader.article.ui.interaction.ArticlesViewEffect.OpenExternalUrl
import com.biondic.rssreader.article.ui.model.ArticleScreenState.Content
import com.biondic.rssreader.article.ui.model.ArticleScreenState.Error
import com.biondic.rssreader.article.ui.model.ArticleScreenState.Loading
import com.biondic.rssreader.core.ui.components.LoadingIndicator
import com.biondic.rssreader.core.ui.components.RefreshableLazyColumn
import com.biondic.rssreader.core.ui.components.RetryScreen
import com.biondic.rssreader.core.ui.components.ScreenTopBar
import org.koin.core.parameter.parametersOf

data class ArticleScreen(
    val url: String,
    val sourceTitle: String,
) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ArticleViewModel =
            koinScreenModel(parameters = { parametersOf(url, sourceTitle) })
        val navigator = LocalNavigator.currentOrThrow
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.viewEffect.collect {
                when (it) {
                    is OpenExternalUrl -> {}
                    is GoBackToSubscriptions -> navigator.pop()
                }
            }
        }

        when (val state = uiState) {
            is Error -> RetryScreen(onRetryClick = { viewModel.onEvent(RefreshCalled) })
            is Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
            is Content -> ArticleScreenComponent(
                uiState = state,
                onEvent = viewModel::onEvent,
            )
        }
    }
}

@Composable
private fun ArticleScreenComponent(
    modifier: Modifier = Modifier,
    uiState: Content,
    onEvent: (ArticlesEvent) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        ScreenTopBar(
            onBackClick = { onEvent(TopBarBackClick) },
            title = uiState.screenTitle,
        )
        RefreshableLazyColumn(
            modifier = modifier.fillMaxSize().padding(8.dp),
            onRefresh = { onEvent(RefreshCalled) },
            refreshing = uiState.isRefreshing,
        ) {
            items(uiState.articles, key = { it.title }) { article ->
                UIArticleComposable(
                    article = article,
                    onArticleClick = { onEvent(ArticleClick(it)) },
                )
            }
        }
    }
}

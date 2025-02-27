package com.biondic.rssreader.article.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biondic.rssreader.article.domain.usecase.GetArticles
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent.ArticleClick
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent.RefreshCalled
import com.biondic.rssreader.article.ui.interaction.ArticlesEvent.TopBarBackClick
import com.biondic.rssreader.article.ui.interaction.ArticlesViewEffect
import com.biondic.rssreader.article.ui.interaction.ArticlesViewEffect.GoBackToSubscriptions
import com.biondic.rssreader.article.ui.interaction.ArticlesViewEffect.OpenExternalUrl
import com.biondic.rssreader.article.ui.mapper.ArticlesUiStateMapper
import com.biondic.rssreader.article.ui.model.ArticleScreenState.Loading
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import model.RefreshStrategy
import model.RefreshStrategy.SyncWithRemote

class ArticleViewModel(
    subscriptionUrl: String,
    sourceTitle: String,
    private val getArticles: GetArticles,
    private val uiMapper: ArticlesUiStateMapper,
) : ViewModel() {
    private val refreshTrigger = Channel<RefreshStrategy>()
    private val _refreshing = MutableStateFlow(false)
    private val _viewEffect = Channel<ArticlesViewEffect>(Channel.BUFFERED)
    val viewEffect = _viewEffect.receiveAsFlow()

    private val _articles = refreshTrigger
        .receiveAsFlow()
        .onStart { emit(SyncWithRemote) }
        .onEach { if (it is SyncWithRemote) _refreshing.value = true }
        .flatMapLatest { strategy ->
            getArticles(
                subscriptionUrl = subscriptionUrl,
                refreshStrategy = strategy,
                refreshingCompleted = { _refreshing.value = false },
            )
        }

    val uiState = combine(
        _refreshing,
        _articles,
        flowOf(sourceTitle),
        uiMapper::map,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Loading,
    )

    fun onEvent(event: ArticlesEvent) {
        when (event) {
            is ArticleClick -> viewModelScope.launch {
                _viewEffect.send(OpenExternalUrl(event.externalUrl))
            }

            is RefreshCalled -> viewModelScope.launch {
                refreshTrigger.send(SyncWithRemote)
            }

            is TopBarBackClick -> viewModelScope.launch {
                _viewEffect.send(GoBackToSubscriptions)
            }
        }
    }
}

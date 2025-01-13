package com.biondic.rssreader.subscriptions.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.biondic.rssreader.core.model.RefreshStrategy
import com.biondic.rssreader.core.model.RefreshStrategy.ReFetchLocalItems
import com.biondic.rssreader.core.model.RefreshStrategy.SyncWithRemote
import com.biondic.rssreader.subscriptions.domain.usecase.SubscriptionsCrudOperations
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.AddButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.FavoriteToggleClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RefreshCalled
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RemoveButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.SubscriptionClick
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.TabSelected
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.UrlFieldChanged
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsViewEffect
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsViewEffect.OpenArticles
import com.biondic.rssreader.subscriptions.ui.mapper.SubscriptionsScreenUiMappers
import com.biondic.rssreader.subscriptions.ui.model.UISubscriptionItem
import com.biondic.rssreader.subscriptions.ui.state.SubscriptionsScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SubscriptionsViewModel(
    private val subscriptionsCrud: SubscriptionsCrudOperations,
    private val uiMappers: SubscriptionsScreenUiMappers,
) : ScreenModel {
    private val _headerState = MutableStateFlow(uiMappers.headerUiMapper.map())
    private val _tabState = MutableStateFlow(uiMappers.tabsUiMapper.map())
    private val refreshTrigger = Channel<RefreshStrategy>()
    private val _refreshing = MutableStateFlow(false)
    private val _viewEffect = Channel<SubscriptionsViewEffect>(Channel.BUFFERED)
    val viewEffect = _viewEffect.receiveAsFlow()

    private val _subscriptions = refreshTrigger
        .receiveAsFlow()
        .onStart { emit(ReFetchLocalItems) }
        .onEach { if (it is SyncWithRemote) _refreshing.update { true } }
        .flatMapLatest { strategy ->
            subscriptionsCrud.getMySubscriptions(
                refreshStrategy = strategy,
                refreshingCompleted = { _refreshing.update { false } },
            )
        }

    val uiState = combine(
        _subscriptions,
        _headerState,
        _tabState,
        _refreshing,
        uiMappers.screenUiMapper::map,
    ).stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SubscriptionsScreenState.Loading,
    )

    fun onEvent(event: SubscriptionsEvent) {
        when (event) {
            is AddButtonClicked -> handleAddButtonClickAction(event.url)
            is FavoriteToggleClicked -> handleFavoriteToggleAction(event.subscription)
            is RefreshCalled -> screenModelScope.launch { refreshTrigger.send(SyncWithRemote) }
            is RemoveButtonClicked -> handleRemoveButtonClickAction(event.url)
            is UrlFieldChanged -> _headerState.update { it.copy(text = event.url, error = null) }
            is TabSelected -> _tabState.update { uiMappers.tabsUiMapper.map(event.tab) }
            is SubscriptionClick -> screenModelScope.launch {
                _viewEffect.send(OpenArticles(event.subscription.url, event.subscription.title))
            }
        }
    }

    private fun handleRemoveButtonClickAction(url: String) = screenModelScope.launch {
        subscriptionsCrud.deleteSubscription(url)
        refreshTrigger.send(ReFetchLocalItems)
    }

    private fun handleFavoriteToggleAction(subscription: UISubscriptionItem) =
        screenModelScope.launch {
            subscriptionsCrud.toggleSubscriptionIsFavoriteState(subscription.toDomain())
            refreshTrigger.send(ReFetchLocalItems)
        }

    private fun handleAddButtonClickAction(url: String) = screenModelScope.launch {
        if (_headerState.value.error != null) {
            _headerState.update { it.copy(error = null, text = "") }
        } else {
            _headerState.update { it.copy(isLoading = true, error = null) }
            subscriptionsCrud.addNewSubscription(url)
                .onRight {
                    refreshTrigger.send(ReFetchLocalItems)
                    _headerState.update { it.copy(text = "") }
                }
                .onLeft { error -> _headerState.update { it.copy(error = "Invalid RSS") } }
            _headerState.update { it.copy(isLoading = false) }
        }
    }
}

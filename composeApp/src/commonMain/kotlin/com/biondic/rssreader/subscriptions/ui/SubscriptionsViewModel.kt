package com.biondic.rssreader.subscriptions.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.biondic.rssreader.core.model.RefreshStrategy
import com.biondic.rssreader.core.model.RefreshStrategy.ReFetchLocalItems
import com.biondic.rssreader.core.model.RefreshStrategy.SyncWithRemote
import com.biondic.rssreader.subscriptions.domain.usecase.AddNewSubscription
import com.biondic.rssreader.subscriptions.domain.usecase.DeleteSubscription
import com.biondic.rssreader.subscriptions.domain.usecase.GetMySubscriptions
import com.biondic.rssreader.subscriptions.domain.usecase.ToggleSubscriptionIsFavoriteState
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.AddButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.FavoriteToggleClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RefreshCalled
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.RemoveButtonClicked
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.SubscriptionClick
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsEvent.UrlFieldChanged
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsViewEffect
import com.biondic.rssreader.subscriptions.ui.interaction.SubscriptionsViewEffect.OpenArticles
import com.biondic.rssreader.subscriptions.ui.mapper.HeaderUiMapper
import com.biondic.rssreader.subscriptions.ui.mapper.SubscriptionsScreenUiMapper
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
    private val getMySubscriptions: GetMySubscriptions,
    private val addNewSubscription: AddNewSubscription,
    private val toggleSubscriptionIsFavoriteState: ToggleSubscriptionIsFavoriteState,
    private val headerUiMapper: HeaderUiMapper,
    private val screenUiMapper: SubscriptionsScreenUiMapper,
    private val deleteSubscription: DeleteSubscription,
) : ScreenModel {
    private val _headerState = MutableStateFlow(headerUiMapper.map())
    private val refreshTrigger = Channel<RefreshStrategy>()
    private val _refreshing = MutableStateFlow(false)
    private val _viewEffect = Channel<SubscriptionsViewEffect>(Channel.BUFFERED)
    val viewEffect = _viewEffect.receiveAsFlow()

    private val _subscriptions = refreshTrigger
        .receiveAsFlow()
        .onStart { emit(ReFetchLocalItems) }
        .onEach { if (it is SyncWithRemote) _refreshing.update { true } }
        .flatMapLatest { strategy ->
            getMySubscriptions(
                refreshStrategy = strategy,
                refreshingCompleted = { _refreshing.update { false } },
            )
        }

    val uiState = combine(
        _subscriptions,
        _headerState,
        _refreshing,
        screenUiMapper::map,
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
            is SubscriptionClick -> screenModelScope.launch {
                _viewEffect.send(OpenArticles(event.subscription.url, event.subscription.title))
            }
        }
    }

    private fun handleRemoveButtonClickAction(url: String) = screenModelScope.launch {
        deleteSubscription(url)
        refreshTrigger.send(ReFetchLocalItems)
    }

    private fun handleFavoriteToggleAction(subscription: UISubscriptionItem) =
        screenModelScope.launch {
            toggleSubscriptionIsFavoriteState(subscription.toDomain())
            refreshTrigger.send(ReFetchLocalItems)
        }

    private fun handleAddButtonClickAction(url: String) = screenModelScope.launch {
        _headerState.update { it.copy(isLoading = true, error = null) }
        addNewSubscription(url)
            .onRight {
                refreshTrigger.send(ReFetchLocalItems)
                _headerState.update { it.copy(text = "") }
            }
            .onLeft { error -> _headerState.update { it.copy(error = "Invalid RSS") } }
        _headerState.update { it.copy(isLoading = false) }
    }
}

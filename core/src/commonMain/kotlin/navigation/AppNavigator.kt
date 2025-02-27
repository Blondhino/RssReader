package navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class AppNavigator : Navigation {
    private val _navigationEvent = Channel<NavigationEvent>(Channel.BUFFERED)
    override val navigationEvent = _navigationEvent.receiveAsFlow()

    override suspend fun emitNavigationEvent(event: NavigationEvent) {
        _navigationEvent.send(event)
    }

    override fun tryEmitNavigationEvent(event: NavigationEvent) {
        _navigationEvent.trySend(event)
    }

}
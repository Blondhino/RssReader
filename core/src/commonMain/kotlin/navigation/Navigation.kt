package navigation

import kotlinx.coroutines.flow.Flow

interface Navigation {
    val navigationEvent: Flow<NavigationEvent>
    suspend fun emitNavigationEvent(event: NavigationEvent)
    fun tryEmitNavigationEvent(event: NavigationEvent)
}
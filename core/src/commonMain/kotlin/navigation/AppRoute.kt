package navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object Subscriptions : AppRoute

    @Serializable
    data class Articles(val url: String, val title: String) : AppRoute
}

package navigation

sealed interface NavigationEvent {
    data class NavigateTo(val route: AppRoute) : NavigationEvent
    data object NavigateBack : NavigationEvent
}
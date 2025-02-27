package com.biondic.rssreader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.biondic.rssreader.article.ui.ArticleScreen
import com.biondic.rssreader.subscriptions.ui.SubscriptionsScreen
import navigation.AppRoute.Articles
import navigation.AppRoute.Subscriptions
import navigation.Navigation
import navigation.NavigationEvent.NavigateBack
import navigation.NavigationEvent.NavigateTo

@Composable
fun NavigationComponent(
    appNavigator: Navigation,
    navController: NavHostController,
) {
    NavHost(
        navController,
        startDestination = Subscriptions,
    ) { generateAppNavigationGraph() }
    LaunchedEffect(appNavigator.navigationEvent) {
        appNavigator.navigationEvent.collect {
            when (it) {
                is NavigateBack -> navController.popBackStack()
                is NavigateTo -> navController.navigate(route = it.route)
            }
        }
    }

}


fun NavGraphBuilder.generateAppNavigationGraph() {
    composable<Subscriptions> { SubscriptionsScreen() }
    composable<Articles> { navBackStackEntry ->
        val route: Articles = navBackStackEntry.toRoute()
        ArticleScreen(url = route.url, sourceTitle = route.title)
    }
}

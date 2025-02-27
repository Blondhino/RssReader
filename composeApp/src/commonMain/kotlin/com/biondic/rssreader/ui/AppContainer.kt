package com.biondic.rssreader.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.biondic.rssreader.navigation.NavigationComponent
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun AppContainer(
    modifier: Modifier = Modifier,
    appNavigator: Navigation = koinInject(),
) {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = modifier) {
            NavigationComponent(appNavigator = appNavigator, navController = navController)
        }
    }
}
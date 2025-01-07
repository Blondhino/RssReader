package com.biondic.rssreader.article

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class ArticleScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ArticleViewModel = koinScreenModel()
        val navigator = LocalNavigator.currentOrThrow
        Column {
            Text(viewModel.getScreenTitle())
            Button(onClick = { navigator.pop() }) {
                Text("Go Back")
            }
        }
    }
}

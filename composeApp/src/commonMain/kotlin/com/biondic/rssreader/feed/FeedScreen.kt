package com.biondic.rssreader.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.biondic.rssreader.article.ArticleScreen

class FeedScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: FeedViewModel = koinScreenModel()
        val navigator = LocalNavigator.currentOrThrow
        Column {
            Text(viewModel.getScreenTitle())
            Button(onClick = { navigator.push(ArticleScreen()) }) {
                Text("Article")
            }
            Button(onClick = { navigator.pop() }) {
                Text("Back")
            }
        }
    }
}

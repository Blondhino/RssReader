package com.biondic.rssreader

import androidx.compose.ui.window.ComposeUIViewController
import com.biondic.rssreader.core.di.platformModule
import com.biondic.rssreader.core.di.sharedModule
import com.biondic.rssreader.core.ui.AppContainer
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = { startKoin { modules(sharedModule(), platformModule()) } },
) {
    AppContainer()
}

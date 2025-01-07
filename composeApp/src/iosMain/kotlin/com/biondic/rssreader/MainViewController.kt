package com.biondic.rssreader

import androidx.compose.ui.window.ComposeUIViewController
import com.biondic.rssreader.core.di.platformModule
import com.biondic.rssreader.core.di.sharedModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = { startKoin { modules(sharedModule(), platformModule()) } },
) {
    AppContainer()
}

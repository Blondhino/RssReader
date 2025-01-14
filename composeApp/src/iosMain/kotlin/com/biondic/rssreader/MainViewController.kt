package com.biondic.rssreader

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.biondic.rssreader.core.di.platformModule
import com.biondic.rssreader.core.di.sharedModule
import com.biondic.rssreader.core.ui.AppContainer
import com.biondic.rssreader.core.ui.darkScheme
import com.biondic.rssreader.core.ui.lightScheme
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin { modules(sharedModule(), platformModule()) }
    return ComposeUIViewController {
        MaterialTheme(
            colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme,
        ) {
            AppContainer(
                modifier = Modifier.padding(
                    top = 50.dp,
                    bottom = 10.dp,
                ),
            )
        }
    }
}

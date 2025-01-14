package com.biondic.rssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.biondic.rssreader.core.ui.AppContainer
import com.biondic.rssreader.core.ui.AppTheme
import com.biondic.rssreader.core.ui.surfaceDark
import com.biondic.rssreader.core.ui.surfaceLight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = surfaceLight.toArgb(),
                darkScrim = surfaceDark.toArgb(),
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = surfaceLight.toArgb(),
                darkScrim = surfaceDark.toArgb(),
            ),
        )
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface,
                ) {
                    Scaffold { innerPadding ->
                        AppContainer(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

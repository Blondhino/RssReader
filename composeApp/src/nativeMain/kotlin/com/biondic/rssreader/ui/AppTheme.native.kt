package com.biondic.rssreader.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.biondic.rssreader.ui.darkScheme
import com.biondic.rssreader.ui.lightScheme

@Composable
actual fun AppTheme(content: @Composable () -> Unit) = MaterialTheme(
    content = content,
    colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme,
)

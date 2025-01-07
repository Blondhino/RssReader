package com.biondic.rssreader.core.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun provideHttpClient(): HttpClient = HttpClient(OkHttp)

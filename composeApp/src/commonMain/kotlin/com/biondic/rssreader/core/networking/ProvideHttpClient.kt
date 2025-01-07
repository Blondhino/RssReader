package com.biondic.rssreader.core.networking

import io.ktor.client.HttpClient

expect fun provideHttpClient(): HttpClient

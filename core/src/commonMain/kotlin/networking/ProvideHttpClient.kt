package networking

import io.ktor.client.HttpClient

expect fun provideHttpClient(): HttpClient

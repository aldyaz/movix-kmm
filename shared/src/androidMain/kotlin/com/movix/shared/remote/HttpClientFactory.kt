package com.movix.shared.remote

import com.movix.shared.utils.NetworkConst
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.json

actual class HttpClientFactory {

    actual fun create(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
            defaultRequest {
                url(NetworkConst.BASE_URL)
            }
            install(ResponseObserver)
        }
    }

}
package com.movix.shared.remote

import io.ktor.client.HttpClient

expect class HttpClientFactory {

    fun create(): HttpClient

}
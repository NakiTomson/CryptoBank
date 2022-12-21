package com.example.remote.core

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(
//    private val tokenLocal: TokenLocalDataSource
) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val urlBuilder = request.url.newBuilder()
        val anonymousToken = ""
        builder.apply {
            addHeader(HEADER_AUTHORIZATION, "Bearer $anonymousToken")
            url(urlBuilder.build())
        }
        return processingResponse(chain.proceed(builder.build()))
    }

    private fun processingResponse(response: Response): Response {
        return response
    }

    companion object {

        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_APP_VERSION = "x-app-version"
        private const val HEADER_SERVER_VERSION = "server_version"
        private const val HEADER_PLATFORM = "x-platform"
        private const val HEADER_LANGUAGE = "language"
    }
}

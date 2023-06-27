package com.example.criticaltechworks_newsapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration

/*This OkHttpClient disables all SSL certificate checks.*/

object AppHttpClient {
    val safeOkHttpClient: OkHttpClient
        get() = try {
            val okHttpClientBuilder = OkHttpClient().newBuilder()

            //Only log apis when in debug mode
             /*if (Buildo.BUILD_TYPE == "debug") {
                 okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                     level = HttpLoggingInterceptor.Level.BODY
                 })
               //  okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
             }*/

            okHttpClientBuilder.addInterceptor(headersInterceptor())
            okHttpClientBuilder.addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                //401 Unauthorized, needs new token, generate new one
                /*    if (response.code == 401 && !chain.request().url.toUri().path.contains("token")) {
                        navigateToLogin()
                    }*/
                response
            }.connectTimeout(Duration.ofSeconds(20))
                .readTimeout(Duration.ofSeconds(20))
                .callTimeout(Duration.ofSeconds(20))
                .writeTimeout(Duration.ofSeconds(20))
            okHttpClientBuilder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
}
package com.example.criticaltechworks_newsapp.data

import com.example.criticaltechworks_newsapp.utils.Global
import okhttp3.Interceptor


fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder().apply {
            addHeader(
                "X-Api-Key", Global.Constants.API_KEY
            )
            addHeader(
                "accept", "application/json"
            )
        }.build()
    )
}

package com.example.criticaltechworks_newsapp.data

import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesResponseModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIInterfaceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiInterface: APIInterface

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        apiInterface = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
            .create(APIInterface::class.java)
    }

    @Test
    fun testGetHeadlines() = runTest {
        val m = MockResponse()
        m.setBody("[]")

        val mockResponse = Response.success(
            (NewsHeadlinesResponseModel(
                status = "", articles = emptyList(), totalResults = 0
            ))
        )

        val deferred = CompletableDeferred<Response<NewsHeadlinesResponseModel>>()
        deferred.complete(mockResponse)

        Mockito.`when`(
            apiInterface.getTopHeadlinesAsync(
                sources = "", page = 0, pageSize = 0
            )
        ).thenReturn(deferred)

        val response = apiInterface.getTopHeadlinesAsync("bbc-news", 10, 1).await()

        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.articles.isEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
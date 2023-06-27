package com.example.criticaltechworks_newsapp.data

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesPagingSource
import com.example.criticaltechworks_newsapp.utils.Global
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesResponseModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PagingSourceTest {
    @Mock
    private lateinit var apiService: APIInterface

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun refresh_returnError() = runTest {
        val errorResponse = Response.error<NewsHeadlinesResponseModel>(
            400, ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\n" + "    \"status\": \"error\",\n" + "    \"code\": \"apiKeyInvalid\",\n" + "    \"message\": \"Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.\"\n" + "}"
            )
        )

        val deferredResponse = CompletableDeferred(errorResponse)
        Mockito.`when`(
            apiService.getTopHeadlinesAsync(
                sources = Global.Constants.HEADLINE_SOURCE_ID,
                page = 1,
                pageSize = 0
            )
        ).thenReturn(deferredResponse)

        val pagingSource = NewsHeadlinesPagingSource(apiInterface = apiService)

        val pager = TestPager(
            PagingConfig(
                pageSize = NewsHeadlinesPagingSource.PageSize,
                enablePlaceholders = false,
                prefetchDistance = 1
            ), pagingSource
        )

        val result = pager.refresh()
        Assert.assertEquals(true, result is PagingSource.LoadResult.Error)
    }
}
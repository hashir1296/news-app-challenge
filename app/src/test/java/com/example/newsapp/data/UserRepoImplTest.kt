package com.example.newsapp.data

import com.example.newsapp.presentation.newsList.NewsHeadlinesResponseModel
import com.example.newsapp.utils.Global
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class UserRepoImplTest {

    @Mock
    private lateinit var apiService: APIInterface

    private lateinit var repo: UserRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repo = UserRepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetHeadlines_EmptyList() = runTest {
        val mockResponse = Response.success(
            NewsHeadlinesResponseModel(
                articles = emptyList(), status = "", totalResults = 0
            )
        )
        val deferredResponse = CompletableDeferred(mockResponse)
        Mockito.`when`(
            apiService.getTopHeadlinesAsync(
                sources = Global.Constants.HEADLINE_SOURCE_ID,
                page = 1,
                pageSize = 0
            )
        ).thenReturn(deferredResponse)

        val result = repo.getNewsHeadlinesFromNetwork(
            source = Global.Constants.HEADLINE_SOURCE_ID, page = 1, pageSize = 0
        )
        Assert.assertEquals(true, result is NetworkResult.Success)

        Assert.assertEquals(
            true, (result as NetworkResult.Success).data?.articles?.isEmpty()
        )
    }


    @Test
    fun testGetHeadlines_expectList() = runTest {
        val mockResponse = Response.success(
            NewsHeadlinesResponseModel(
                articles = getArticlesList(), status = "", totalResults = 1
            )
        )
        val deferredResponse = CompletableDeferred(mockResponse)
        Mockito.`when`(
            apiService.getTopHeadlinesAsync(
                sources = Global.Constants.HEADLINE_SOURCE_ID,
                page = 1,
                pageSize = 1
            )
        ).thenReturn(deferredResponse)

        val result = repo.getNewsHeadlinesFromNetwork(
            source = Global.Constants.HEADLINE_SOURCE_ID, page = 1, pageSize = 1
        )
        Assert.assertEquals(true, result is NetworkResult.Success)

        Assert.assertEquals(
            1, (result as NetworkResult.Success).data?.articles?.size
        )
    }

    @Test
    fun testGetHeadlines_EmptyListWhenSourceIsInvalid() = runTest {
        val mockResponse = Response.success(
            NewsHeadlinesResponseModel(
                articles = emptyList(), status = "ok", totalResults = 0
            )
        )
        val deferredResponse = CompletableDeferred(mockResponse)
        Mockito.`when`(
            apiService.getTopHeadlinesAsync(
                sources = "this source is not supported by server",
                page = 0,
                pageSize = 0
            )
        ).thenReturn(deferredResponse)

        val result = repo.getNewsHeadlinesFromNetwork(
            source = "this source is not supported by server",
            page = 0,
            pageSize = 0
        )
        Assert.assertEquals(true, result is NetworkResult.Success)

        Assert.assertEquals(
            0, (result as NetworkResult.Success).data?.articles?.size
        )
    }

    private fun getArticlesList(): List<NewsHeadlinesResponseModel.Article> {
        return listOf(
            NewsHeadlinesResponseModel.Article(
                title = "Some title",
                content = "Some content",
                description = "Some description",
                author = "Some author",
                source = NewsHeadlinesResponseModel.Article.Source(
                    id = null, name = ""
                ),
                publishedAt = "",
                url = "",
                urlToImage = ""
            )
        )
    }
}
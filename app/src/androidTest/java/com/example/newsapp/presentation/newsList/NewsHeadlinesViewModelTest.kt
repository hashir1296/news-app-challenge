package com.example.newsapp.presentation.newsList

import org.junit.Assert.*

import androidx.test.runner.AndroidJUnit4
import com.example.newsapp.data.APIInterface
import com.example.newsapp.data.UserRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NewsHeadlinesViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: APIInterface

    @Inject
    lateinit var repository: UserRepositoryImpl

    private lateinit var viewModel: NewsHeadlinesViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = NewsHeadlinesViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_if_response_model_maps_correctly_to_domain_model() {
        val articleItem = NewsHeadlinesResponseModel.Article(
            author = "John Doe",
            content = "Sample content",
            description = "Sample description",
            publishedAt = "2023-06-25T15:37:17.2152974Z",
            source = NewsHeadlinesResponseModel.Article.Source(
                id = "sourceId", name = "Source Name"
            ),
            title = "Sample Title",
            url = "https://example.com",
            urlToImage = "https://example.com/image.jpg"
        )

        val mappedItem = articleItem.toDomainModel()

        assert(mappedItem.title == articleItem.title)
        assert(mappedItem.description == articleItem.description)
    }
}
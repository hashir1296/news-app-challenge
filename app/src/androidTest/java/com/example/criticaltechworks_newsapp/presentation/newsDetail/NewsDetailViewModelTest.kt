package com.example.criticaltechworks_newsapp.presentation.newsDetail

import androidx.core.text.toSpanned
import app.cash.turbine.test
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlineDomainModel
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsDetailViewModelTest {
    private lateinit var viewModel: NewsDetailViewModel

    private lateinit var dummy: NewsHeadlineDomainModel

    @Before
    fun setup() {
        viewModel = NewsDetailViewModel()
        dummy = NewsHeadlineDomainModel(
            title = "Title",
            author = "Author",
            content = "".toSpanned(),
            description = "Description",
            publishedDate = "2023-06-25T15:37:17.2152974Z",
            imageURL = "\"https://ichef.bbci.co.uk/news/1024/branded_news/727E/production/_130201392_c3c364ff33d64e710c9bda31f2da6d5d7234926d-1.jpg\""
        )
        viewModel.getNewsDetails(dummy)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun testFlow_TitleIsSetCorrectly() = runTest {

        viewModel.title.test {
            val emission = awaitItem()
            assert(emission == dummy.title)
        }
    }

    @Test
    fun testFlow_ImageIsSetCorrectly() = runTest {

        viewModel.imageURL.test {
            val emission = awaitItem()
            assert(emission == dummy.imageURL)
        }
    }

    @Test
    fun testFlow_DescIsSetCorrectly() = runTest {
        viewModel.description.test {
            val emission = awaitItem()
            assert(emission == dummy.description)
        }
    }

    @Test
    fun testFlow_ContentIsSetCorrectly() = runTest {
        viewModel.content.test {
            val emission = awaitItem()
            assert(emission == dummy.content)
        }
    }

}
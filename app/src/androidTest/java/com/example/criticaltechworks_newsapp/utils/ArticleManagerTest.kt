package com.example.criticaltechworks_newsapp.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesResponseModel
import com.google.gson.JsonSyntaxException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class ArticleManagerTest {

    lateinit var articleManager: ArticleManager
    lateinit var context: Context

    @Before
    fun setUp() {
        articleManager = ArticleManager()
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @After
    fun tearDown() {
    }

    @Test(expected = FileNotFoundException::class)
    fun populateArticlesFromJsonFile() {
        //throws FileNotFoundException and thats how it should be because filename is invalid
        articleManager.populateArticlesFromJsonFile(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateArticlesFromJsonFile_InvalidJson_expected_Exception() {
        //throws FileNotFoundException and thats how it should be because filename is invalid
        articleManager.populateArticlesFromJsonFile(context, "malformed.json")
    }

    @Test
    fun populateArticlesFromJsonFile_ValidJson_expected_Count() {
        //throws FileNotFoundException and thats how it should be because filename is invalid
        articleManager.populateArticlesFromJsonFile(context, "articles.json")
        assertEquals(10, articleManager.articlesList.size)
    }

    @Test
    fun testPreviousArticle_expected_CorrectArticle() {
        articleManager.populateArticles(arrayOf(
                NewsHeadlinesResponseModel.Article(
                    author = "1",
                    content = "Article 1",
                    description = "Description 1",
                    publishedAt = "",
                    url = "",
                    source = NewsHeadlinesResponseModel.Article.Source("", ""),
                    title = "Title 1",
                    urlToImage = ""
                ),
                NewsHeadlinesResponseModel.Article(
                    author = "2",
                    content = "Article 2",
                    description = "Description 2",
                    publishedAt = "",
                    url = "",
                    source = NewsHeadlinesResponseModel.Article.Source("", ""),
                    title = "Title 2",
                    urlToImage = ""
                ),
                NewsHeadlinesResponseModel.Article(
                    author = "3",
                    content = "Article 3",
                    description = "Description 3",
                    publishedAt = "",
                    url = "",
                    source = NewsHeadlinesResponseModel.Article.Source("", ""),
                    title = "Title 3",
                    urlToImage = ""
                )
            ))
        val article = articleManager.getPreviousArticle()
        assertEquals("1", article.author)
    }

    @Test
    fun testNextArticle_expected_CorrectArticle() {
        articleManager.populateArticles(arrayOf(
            NewsHeadlinesResponseModel.Article(
                author = "1",
                content = "Article 1",
                description = "Description 1",
                publishedAt = "",
                url = "",
                source = NewsHeadlinesResponseModel.Article.Source("", ""),
                title = "Title 1",
                urlToImage = ""
            ),
            NewsHeadlinesResponseModel.Article(
                author = "2",
                content = "Article 2",
                description = "Description 2",
                publishedAt = "",
                url = "",
                source = NewsHeadlinesResponseModel.Article.Source("", ""),
                title = "Title 2",
                urlToImage = ""
            ),
            NewsHeadlinesResponseModel.Article(
                author = "3",
                content = "Article 3",
                description = "Description 3",
                publishedAt = "",
                url = "",
                source = NewsHeadlinesResponseModel.Article.Source("", ""),
                title = "Title 3",
                urlToImage = ""
            )
        ))
        val article = articleManager.getNextArticle()
        assertEquals("2", article.author)
    }
}
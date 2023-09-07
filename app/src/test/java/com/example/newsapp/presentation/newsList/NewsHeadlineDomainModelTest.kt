package com.example.newsapp.presentation.newsList

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsHeadlineDomainModelTest {

    @Test
    fun `response model should correctly map to domain model`() {
        //Arrange
        val dummyResponseModel = NewsListResponseModel(
            status = "", totalResults = 1, articles = listOf(
                NewsListResponseModel.Article(
                    author = "Author 1",
                    title = "Title 1",
                    urlToImage = "some link to image",
                    url = "some url to article",
                    source = NewsListResponseModel.Article.Source(id = "", name = "BBC"),
                    publishedAt = "",
                    description = "Description 1",
                    content = "A draft law banning speech and dressing \"detrimental to the spirit of Chinese people\" has sparked debate in China.\r\nIf the law comes into force, people found guilty could be fined or jailed but the p… [+2939 chars]"
                )
            )
        )
        //Act
        val sut = dummyResponseModel.articles[0].toDomainModel()

        //Assert
        assertEquals("Title 1", sut.title)
        assertEquals("Description 1", sut.description)
        assertEquals("Author 1", sut.author)
    }


}
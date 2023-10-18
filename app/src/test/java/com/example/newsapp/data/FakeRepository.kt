package com.example.newsapp.data

import com.example.newsapp.presentation.newsList.NewsListPagingSource
import com.example.newsapp.presentation.newsList.NewsListResponseModel

class FakeRepository : UserRepository {

    //private val
    override suspend fun getNewsHeadlinesFromNetwork(

        : Int,
        page: Int,
        source: String,
        searchQuery: String?
    ): NetworkResult<NewsListResponseModel> {
        TODO()
    }

    override fun getPagingSource(searchQuery: String?): NewsListPagingSource {
        TODO()
    }
}
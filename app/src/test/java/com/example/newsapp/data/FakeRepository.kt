package com.example.newsapp.data

import com.example.newsapp.presentation.newsList.NewsListPagingSource
import com.example.newsapp.presentation.newsList.NewsListResponseModel

class FakeRepository : UserRepository {

    //private val
    override suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int,
        page: Int,
        source: String,
        searchQuery: String?
    ): NetworkResult<NewsListResponseModel> {

    }

    override fun getPagingSource(searchQuery: String?): NewsListPagingSource {
        TODO()
    }
}
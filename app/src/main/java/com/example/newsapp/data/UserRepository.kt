package com.example.newsapp.data

import com.example.newsapp.presentation.newsList.NewsListPagingSource
import com.example.newsapp.presentation.newsList.NewsListResponseModel

interface UserRepository {

    suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int, page: Int, source: String, searchQuery: String?
    ): NetworkResult<NewsListResponseModel>

    fun getPagingSource(searchQuery : String?): NewsListPagingSource

}
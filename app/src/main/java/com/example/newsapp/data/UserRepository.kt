package com.example.newsapp.data

import com.example.newsapp.presentation.newsList.NewsHeadlinesPagingSource
import com.example.newsapp.presentation.newsList.NewsHeadlinesResponseModel

interface UserRepository {

    suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int, page: Int, source: String
    ): NetworkResult<NewsHeadlinesResponseModel>

    fun getPagingSource(): NewsHeadlinesPagingSource


}
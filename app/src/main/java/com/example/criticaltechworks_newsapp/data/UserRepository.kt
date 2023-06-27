package com.example.criticaltechworks_newsapp.data

import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesPagingSource
import com.example.newsapp_nongitversion.features.newsList.NewsHeadlinesResponseModel

interface UserRepository {

    suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int, page: Int, source: String
    ): NetworkResult<NewsHeadlinesResponseModel>

    fun getPagingSource(): NewsHeadlinesPagingSource


}
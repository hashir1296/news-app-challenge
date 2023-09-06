package com.example.newsapp.data

import com.example.newsapp.presentation.newsList.NewsListPagingSource
import com.example.newsapp.presentation.newsList.NewsListResponseModel
import com.google.gson.Gson


class UserRepositoryImpl(
    private val apiInterface: APIInterface
) : UserRepository {

    override suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int, page: Int, source: String, searchQuery: String?
    ): NetworkResult<NewsListResponseModel> {

        val api = apiInterface.getTopHeadlinesAsync(
            page = page, pageSize = pageSize, sources = source, query = searchQuery
        ).await()
        return try {
            when {
                api.isSuccessful -> {
                    NetworkResult.Success(data = api.body())
                }

                else -> {
                    val errorBody = Gson().fromJson(
                        api.errorBody()?.string(),
                        GenericErrorResponseModel::class.java
                    )
                    NetworkResult.Error(
                        message = errorBody.message,
                        status = errorBody.status,
                        code = errorBody.code
                    )
                }
            }
        } catch (ex: Exception) {
            NetworkResult.Error(
                message = ex.message
            )
        }
    }

    override fun getPagingSource(searchQuery : String?) = NewsListPagingSource(apiInterface, searchQuery)

}
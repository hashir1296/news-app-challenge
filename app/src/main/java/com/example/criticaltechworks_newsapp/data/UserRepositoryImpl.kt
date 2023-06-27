package com.example.criticaltechworks_newsapp.data

import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesPagingSource
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesResponseModel
import com.google.gson.Gson


class UserRepositoryImpl(
    private val apiInterface: APIInterface
) : UserRepository {

    override suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int, page: Int, source: String
    ): NetworkResult<NewsHeadlinesResponseModel> {

        val api = apiInterface.getTopHeadlinesAsync(
            page = page, pageSize = pageSize, sources = source
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

    override fun getPagingSource() = NewsHeadlinesPagingSource(apiInterface)

}
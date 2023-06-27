package com.example.criticaltechworks_newsapp.data

import com.example.criticaltechworks_newsapp.utils.Global
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlinesResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET(Global.Constants.EndPoints.TOP_HEADLINES)
    fun getTopHeadlinesAsync(
        @Query("sources") sources: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Deferred<Response<NewsHeadlinesResponseModel>>

}
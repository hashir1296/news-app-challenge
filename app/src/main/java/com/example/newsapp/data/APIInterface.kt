package com.example.newsapp.data

import com.example.newsapp.utils.Global
import com.example.newsapp.presentation.newsList.NewsListResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET(Global.Constants.EndPoints.TOP_HEADLINES)
     fun getTopHeadlinesAsync(
        @Query("sources") sources: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("q") query : String?
    ): Deferred<Response<NewsListResponseModel>>

}
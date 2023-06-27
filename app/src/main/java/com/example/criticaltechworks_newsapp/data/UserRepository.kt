package com.example.criticaltechworks_newsapp.data

interface UserRepository {

    suspend fun getNewsHeadlinesFromNetwork(
        pageSize: Int, page: Int, source: String
    ): NetworkResult<Void>


}
package com.example.newsapp.presentation.newsList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.APIInterface
import com.example.newsapp.utils.Global
import com.example.newsapp.utils.sortByDate

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NewsHeadlinesPagingSource(
    private val apiInterface: APIInterface
) : PagingSource<Int, NewsHeadlineDomainModel>() {

    companion object {
        const val PageSize = 20
    }


    override fun getRefreshKey(state: PagingState<Int, NewsHeadlineDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsHeadlineDomainModel> {
        return try {
            val pageNumber = params.key ?: 1
            withContext(Dispatchers.IO) {
                val api = apiInterface.getTopHeadlinesAsync(
                    page = pageNumber,
                    pageSize = PageSize,
                    sources = Global.Constants.HEADLINE_SOURCE_ID
                ).await()
                withContext(Dispatchers.Main) {
                    if (api.isSuccessful) {
                        if (api.body() != null) {
                            val response = api.body()
                            val articles =
                                response?.articles?.sortByDate()?.map {
                                    it.toDomainModel()
                                }
                            LoadResult.Page(
                                data = articles ?: emptyList(),
                                nextKey = if (articles?.isEmpty() == true) null else pageNumber.plus(
                                    1
                                ),
                                prevKey = null
                            )
                        } else {
                            LoadResult.Error(Exception())
                        }
                    } else {
                        LoadResult.Error(Exception())
                    }
                }
            }
        } catch (ex: Exception) {
            withContext(Dispatchers.Main) {
                LoadResult.Error(ex)
            }
        }
    }


    override val keyReuseSupported: Boolean
        get() = true

}
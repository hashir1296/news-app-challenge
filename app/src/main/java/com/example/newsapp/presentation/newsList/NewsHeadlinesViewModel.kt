package com.example.newsapp.presentation.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsHeadlinesViewModel @Inject constructor(private val repository: UserRepositoryImpl) :
    ViewModel() {

    var items: Flow<PagingData<NewsHeadlineDomainModel>> = Pager(
        config = PagingConfig(
            pageSize = NewsHeadlinesPagingSource.PageSize,
            enablePlaceholders = false,
            prefetchDistance = 1
        ),
        pagingSourceFactory = { repository.getPagingSource() }).flow.cachedIn(
        viewModelScope
    )
}
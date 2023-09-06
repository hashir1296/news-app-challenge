package com.example.newsapp.presentation.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsapp.data.UserRepositoryImpl
import com.example.newsapp.utils.ListViewType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val repository: UserRepositoryImpl) :
    ViewModel() {


    private var _listViewType = MutableStateFlow(ListViewType.LINEAR)
    val listViewType = _listViewType.asStateFlow()


    private var currentQuery: String? = null


    fun setListViewType(listViewType: ListViewType) {
        _listViewType.value = listViewType
    }


    fun searchData(query: String) {
        if (query != currentQuery) {
            currentQuery = query
        }
    }

    var items: Flow<PagingData<NewsHeadlineDomainModel>> = Pager(
        config = PagingConfig(
            pageSize = NewsListPagingSource.PageSize,
            enablePlaceholders = false,
            prefetchDistance = 1
        ),
        pagingSourceFactory = { repository.getPagingSource(searchQuery = currentQuery) }).flow.cachedIn(
        viewModelScope
    )
}
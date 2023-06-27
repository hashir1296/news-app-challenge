package com.example.criticaltechworks_newsapp.presentation.newsDetail

import androidx.core.text.toSpanned
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criticaltechworks_newsapp.presentation.newsList.NewsHeadlineDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor() : ViewModel() {


    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _imageURL = MutableStateFlow("")
    val imageURL = _imageURL.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _content = MutableStateFlow("".toSpanned())
    val content = _content.asStateFlow()

    fun getNewsDetails(newsItem: NewsHeadlineDomainModel) {
        viewModelScope.launch(Dispatchers.Main) {
            _title.value = newsItem.title
            _imageURL.value = newsItem.imageURL
            _description.value = newsItem.description
            _content.value = newsItem.content ?: "".toSpanned()
        }
    }
}






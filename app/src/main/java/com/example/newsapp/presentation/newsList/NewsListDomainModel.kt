package com.example.newsapp.presentation.newsList

import android.os.Parcelable
import android.text.Spanned
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NewsHeadlineDomainModel(
    val title: String,
    val imageURL: String,
    val author: String,
    val publishedDate: String,
    val description: String,
    val content: String
) : Parcelable


fun NewsListResponseModel.Article.toDomainModel(): NewsHeadlineDomainModel {
    return NewsHeadlineDomainModel(
        title = this.title ?: "",
        imageURL = this.urlToImage ?: "",
        author = this.author ?: "",
        publishedDate = this.publishedAt ?: "",
        description = this.description ?: "",
        content = this.content ?: ""
    )
}
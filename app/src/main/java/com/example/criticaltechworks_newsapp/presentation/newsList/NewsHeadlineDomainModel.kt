package com.example.criticaltechworks_newsapp.presentation.newsList

import android.os.Parcelable
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.newsapp_nongitversion.features.newsList.NewsHeadlinesResponseModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class NewsHeadlineDomainModel(
    val title: String,
    val imageURL: String,
    val author: String,
    val publishedDate: String,
    val description: String,
    val content: @RawValue Spanned
) : Parcelable


fun NewsHeadlinesResponseModel.Article.toDomainModel(): NewsHeadlineDomainModel {
    return NewsHeadlineDomainModel(
        title = this.title ?: "",
        imageURL = this.urlToImage ?: "",
        author = this.author ?: "",
        publishedDate = this.publishedAt ?: "",
        description = this.description ?: "",
        content = HtmlCompat.fromHtml(
            this.content?:"", HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    )
}
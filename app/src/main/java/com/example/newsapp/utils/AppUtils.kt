package com.example.newsapp.utils

import android.text.Spanned
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.newsapp.presentation.newsList.NewsListResponseModel
import com.google.android.material.textview.MaterialTextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun List<NewsListResponseModel.Article>.sortByDate() =
    sortedByDescending {
        it.publishedAt
    }


@BindingAdapter("bind:loadImage")
fun loadImage(imageView: AppCompatImageView, imageURL: String) {
    imageView.load(imageURL)
}

@BindingAdapter("bind:loadHtml")
fun loadImage(materialTextView: MaterialTextView, spanned: Spanned) {
    materialTextView.text = spanned
}

fun convertDateFormat(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
    val outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy, HH:mm")
    val dateTime = LocalDateTime.parse(dateString, inputFormatter)
    return dateTime.format(outputFormatter)
}


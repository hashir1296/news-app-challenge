package com.example.newsapp.utils

import android.text.Spanned
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.newsapp.presentation.newsList.NewsListResponseModel
import com.google.android.material.textview.MaterialTextView

fun List<NewsListResponseModel.Article>.sortByDate() =
    sortedByDescending {
        it.publishedAt
    }


@BindingAdapter("bind:loadImage")
fun loadImage(imageView: AppCompatImageView, imageURL: String) {
    imageView.load(imageURL)
}

@BindingAdapter("bind:loadHtml")
fun loadImage(materialTextView: MaterialTextView, spanned: Spanned?) {
    spanned?.let {
        materialTextView.text = spanned
    }
}


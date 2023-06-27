package com.example.criticaltechworks_newsapp.utils

import com.example.criticaltechworks_newsapp.BuildConfig


object Global {
    object Variables {
        var BASE_URL = "https://newsapi.org/"
    }

    object Constants {
        /*API Key should never be stored like this. This could be reversed engineered and could cause data leaks*/
        const val API_KEY = BuildConfig.NEWS_X_API_KEY
        const val HEADLINE_SOURCE_ID =BuildConfig.NEWS_SOURCE_ID
        const val HEADLINE_SOURCE = BuildConfig.NEWS_SOURCE_TITLE

        object EndPoints {
            const val TOP_HEADLINES = "/v2/top-headlines"
        }

    }
}

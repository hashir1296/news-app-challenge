@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        buildConfigField(
            "String", "NEWS_X_API_KEY", "${project.property("NEWS_X_API_KEY")}"
        )

        testInstrumentationRunner =
            "com.example.criticaltechworks_newsapp.CustomRunner"
    }

    packaging {
        resources.excludes.add("META-INF/INDEX.LIST")
        resources.excludes.add("META-INF/DEPENDENCIES")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    flavorDimensions += "news-source"
    productFlavors {
        create("bbc-news") {
            dimension = "news-source"
            applicationIdSuffix = ".bbc"
            versionNameSuffix = "-bbc_news"

            buildConfigField(
                "String", "NEWS_SOURCE_ID", "\"bbc-news\""
            )
            buildConfigField(
                "String", "NEWS_SOURCE_TITLE", "\"BCC NEWS\""
            )
        }

        create("abc-news") {
            dimension = "news-source"
            applicationIdSuffix = ".abc"
            versionNameSuffix = "-abc_news"

            buildConfigField(
                "String", "NEWS_SOURCE_ID", "\"abc-news\""
            )
            buildConfigField(
                "String", "NEWS_SOURCE_TITLE", "\"ABC NEWS\""
            )
        }

    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    dataBinding {
        android.buildFeatures.dataBinding = true
    }
    testOptions {
        packaging {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }
}

dependencies {
    implementation(
        fileTree(
            mapOf(
                "dir" to "libs", "include" to listOf("*.jar")
            )
        )
    )

    val pagingVersion = "3.2.0"

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.21")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")
    implementation("androidx.core:core-ktx:1.10.1")

    implementation("com.google.android.material:material:1.9.0")

    //Timber - Used for logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Sdp & ssp library for responsive dimensions - it will generate its own dimen file so we don"t have to maintain ours manually
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    //Coil - for loading libraries
    implementation("io.coil-kt:coil:2.4.0")

    //Navigation component
    val navVersion ="2.7.1"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Okhttp & Stetho
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Kotlin Coroutines
    val coroutinesVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    //Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    //Paging Library
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")

    //Biometric verification
    implementation("androidx.biometric:biometric:1.1.0")

    //Swipe refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")



    //Testing libraries - Local Unit tests
    testImplementation("junit:junit:4.13.2")


    //Testing libraries
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")


}
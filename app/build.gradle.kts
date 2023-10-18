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
    namespace = "com.example.criticaltechworks_newsapp"
    compileSdk = 33
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.example.criticaltechworks_newsapp"
        minSdk = 26
        targetSdk = 33
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

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.21")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha02")
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
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.6.0")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("androidx.navigation:navigation-testing:2.6.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testImplementation("junit:junit:4.12")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    //Okhttp & Stetho
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.facebook.stetho:stetho-okhttp3:1.6.0")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
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
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    //Testing libraries - Local Unit tests
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.11")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("androidx.paging:paging-common:3.1.1")
    testImplementation("androidx.paging:paging-common:3.1.1")
    testImplementation("androidx.paging:paging-runtime:3.1.1")
    testImplementation("androidx.paging:paging-testing:3.2.0-rc01")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("junit:junit:4.13.2")
    kaptTest("com.google.dagger:hilt-android-compiler:2.44")


    //Testing libraries - Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.3")
    androidTestImplementation("org.mockito:mockito-android:2.7.15")

    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
    androidTestImplementation("androidx.paging:paging-common:3.1.1")
    androidTestImplementation("androidx.paging:paging-runtime:3.1.1")
    androidTestImplementation("androidx.paging:paging-testing:3.2.0-rc01")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

    debugImplementation("androidx.fragment:fragment-testing:1.6.0")
    implementation("androidx.biometric:biometric:1.1.0")


}
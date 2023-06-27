package com.example.criticaltechworks_newsapp.di

import android.content.Context
import com.example.criticaltechworks_newsapp.data.APIInterface
import com.example.criticaltechworks_newsapp.data.AppHttpClient
import com.example.criticaltechworks_newsapp.data.UserRepositoryImpl
import com.example.criticaltechworks_newsapp.utils.Global
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class) //This is a singleton that lives in our as long as the application lives
@Module
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit(
        @ApplicationContext context: Context,
        moshi: Moshi,
        httpClient: OkHttpClient
    ): Retrofit.Builder =
        Retrofit.Builder().baseUrl(Global.Variables.BASE_URL).client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(
                MoshiConverterFactory.create(moshi).asLenient()
            )

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit.Builder): APIInterface {
        return retrofit.build().create(APIInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshiBuilder(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        apiInterface: APIInterface
    ): UserRepositoryImpl {
        return UserRepositoryImpl(apiInterface = apiInterface)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = AppHttpClient.safeOkHttpClient

}
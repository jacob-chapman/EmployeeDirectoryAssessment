package com.jacobchapman.network.di

import com.jacobchapman.network.api.EmployeeDirectoryApi
import com.jacobchapman.network.adapters.ApiResponseAdapterFactory
import com.jacobchapman.network.models.EmployeeTypeAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder()
        .add(EmployeeTypeAdapter())
        .add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseAdapterFactory.create())

    }


    @Provides
    @Singleton
    fun provideEmployeeDirectoryApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder
    ): EmployeeDirectoryApi {
        return retrofitBuilder.client(okHttpClientBuilder.build())
            .baseUrl("https://s3.amazonaws.com/sq-mobile-interview/").build()
            .create(EmployeeDirectoryApi::class.java)
    }
}


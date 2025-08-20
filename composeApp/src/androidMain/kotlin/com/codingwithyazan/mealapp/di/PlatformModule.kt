package com.codingwithyazan.mealapp.di

import com.codingwithyazan.mealapp.data.remote.MealApiService
import com.codingwithyazan.mealapp.data.remote.RetrofitMealApiService
import com.codingwithyazan.mealapp.data.remote.AndroidMealApiService
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object PlatformModule {
    
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    
    private val json = Json {
        ignoreUnknownKeys = true
    }
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    
    fun createMealApiService(retrofit: Retrofit): MealApiService {
        val retrofitService = retrofit.create(RetrofitMealApiService::class.java)
        return AndroidMealApiService(retrofitService)
    }
}

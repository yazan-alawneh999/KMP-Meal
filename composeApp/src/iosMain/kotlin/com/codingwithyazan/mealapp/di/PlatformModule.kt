package com.codingwithyazan.mealapp.di

import com.codingwithyazan.mealapp.data.remote.MealApiService
import com.codingwithyazan.mealapp.data.remote.IosMealApiService
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object PlatformModule {
    
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    
    private val json = Json {
        ignoreUnknownKeys = true
    }
    
    fun createHttpClient(): HttpClient {
        return HttpClient(Darwin) {
            install(ContentNegotiation) {
                json(json)
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
    
    fun createMealApiService(): MealApiService {
        val httpClient = createHttpClient()
        return IosMealApiService(httpClient)
    }
}

package com.codingwithyazan.mealapp.data.remote

import com.codingwithyazan.mealapp.domain.meal.MealDetailResponse
import com.codingwithyazan.mealapp.domain.meal.MealsResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class IosMealApiService(
    private val httpClient: HttpClient
) : MealApiService {
    
    private companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
    
    override suspend fun getMealsByCategory(category: String): MealsResponse {
        return httpClient.get("${BASE_URL}filter.php") {
            parameter("c", category)
        }.body()
    }
    
    override suspend fun getMealById(mealId: String): MealDetailResponse {
        return httpClient.get("${BASE_URL}lookup.php") {
            parameter("i", mealId)
        }.body()
    }
}

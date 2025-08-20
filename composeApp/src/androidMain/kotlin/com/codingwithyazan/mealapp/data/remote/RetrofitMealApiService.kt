package com.codingwithyazan.mealapp.data.remote

import com.codingwithyazan.mealapp.domain.meal.MealDetailResponse
import com.codingwithyazan.mealapp.domain.meal.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMealApiService {
    
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse
    
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: String): MealDetailResponse
}

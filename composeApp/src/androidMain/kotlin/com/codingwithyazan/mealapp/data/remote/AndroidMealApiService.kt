package com.codingwithyazan.mealapp.data.remote

import com.codingwithyazan.mealapp.domain.meal.MealDetailResponse
import com.codingwithyazan.mealapp.domain.meal.MealsResponse

class AndroidMealApiService(
    private val retrofitService: RetrofitMealApiService
) : MealApiService {
    
    override suspend fun getMealsByCategory(category: String): MealsResponse {
        return retrofitService.getMealsByCategory(category)
    }
    
    override suspend fun getMealById(mealId: String): MealDetailResponse {
        return retrofitService.getMealById(mealId)
    }
}

package com.codingwithyazan.mealapp.data.remote

import com.codingwithyazan.mealapp.domain.meal.MealDetailResponse
import com.codingwithyazan.mealapp.domain.meal.MealsResponse

interface MealApiService {
    
    suspend fun getMealsByCategory(category: String): MealsResponse
    
    suspend fun getMealById(mealId: String): MealDetailResponse
}

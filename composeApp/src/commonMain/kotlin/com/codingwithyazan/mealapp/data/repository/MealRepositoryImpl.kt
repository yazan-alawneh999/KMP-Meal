package com.codingwithyazan.mealapp.data.repository

import com.codingwithyazan.mealapp.data.remote.MealApiService
import com.codingwithyazan.mealapp.domain.meal.Meal
import com.codingwithyazan.mealapp.domain.meal.MealDetail
import com.codingwithyazan.mealapp.domain.meal.MealRepository

class MealRepositoryImpl(
    private val apiService: MealApiService
) : MealRepository {
    
    override suspend fun getMealsByCategory(category: String): List<Meal> {
        return try {
            val response = apiService.getMealsByCategory(category)
            response.meals ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getMealById(mealId: String): MealDetail? {
        return try {
            val response = apiService.getMealById(mealId)
            response.meals?.firstOrNull()
        } catch (e: Exception) {
            null
        }
    }
}


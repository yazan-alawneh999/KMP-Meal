package com.codingwithyazan.mealapp.domain.meal

interface MealRepository {
    suspend fun getMealsByCategory(category: String): List<Meal>
    suspend fun getMealById(mealId: String): MealDetail?
}


package com.codingwithyazan.mealapp.data.repository

import com.codingwithyazan.mealapp.data.remote.MealApiService
import com.codingwithyazan.mealapp.domain.meal.Meal
import com.codingwithyazan.mealapp.domain.meal.MealDetail
import com.codingwithyazan.mealapp.domain.meal.MealRepository

class MealRepositoryImpl(
    private val apiService: MealApiService
) : MealRepository {
    
    override suspend fun getMealsByCategory(category: String): List<Meal> {
        val response = apiService.getMealsByCategory(category)
        val rawMeals = response.meals ?: emptyList()
        
        // Filter out meals with missing required properties
        val validMeals = rawMeals.filterNotNull().filter { meal ->
            val isValid = meal.isValid()
            
            if (!isValid) {
                println("Repository: Filtering out invalid meal - idMeal: '${meal.idMeal}', strMeal: '${meal.strMeal}', strMealThumb: '${meal.strMealThumb}'")
            }
            
            isValid
        }

        // Log the response for debugging
        println("Repository: API response for $category - raw meals: ${rawMeals.size}, valid meals: ${validMeals.size}")
        if (validMeals.isNotEmpty()) {
            println("Repository: First valid meal: ${validMeals.first().strMeal}")
        } else {
            println("Repository: WARNING - No valid meals returned for $category")
            if (rawMeals.isNotEmpty()) {
                println("Repository: Raw meals had ${rawMeals.size} items but all were invalid")
            }
        }
        
        return validMeals
    }
    
    override suspend fun getMealById(mealId: String): MealDetail? {
        val response = apiService.getMealById(mealId)
        val meal = response.meals?.firstOrNull()
        
        // Log the response for debugging
        if (meal != null) {
            println("Repository: Found meal by ID $mealId: ${meal.strMeal}")
        } else {
            println("Repository: WARNING - No meal found for ID $mealId")
        }
        
        return meal
    }
}


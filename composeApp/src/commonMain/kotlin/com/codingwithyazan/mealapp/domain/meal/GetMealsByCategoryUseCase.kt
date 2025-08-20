package com.codingwithyazan.mealapp.domain.meal

import com.codingwithyazan.mealapp.domain.core.BaseUseCase
import com.codingwithyazan.mealapp.domain.core.DataState

class GetMealsByCategoryUseCase(
    private val repository: MealRepository
) : BaseUseCase<GetMealsByCategoryUseCase.Params, List<Meal>>() {
    
    data class Params(val category: String)
    
    override suspend fun execute(params: Params): DataState<List<Meal>> {
        return try {
            val meals = repository.getMealsByCategory(params.category)
            if (meals.isNotEmpty()) {
                DataState.Success(meals)
            } else {
                DataState.Error("No meals found for category: ${params.category}")
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error occurred")
        }
    }
}


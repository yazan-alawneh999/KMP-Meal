package com.codingwithyazan.mealapp.domain.meal

import com.codingwithyazan.mealapp.domain.core.BaseUseCase
import com.codingwithyazan.mealapp.domain.core.DataState

class GetMealByIdUseCase(
    private val repository: MealRepository
) : BaseUseCase<GetMealByIdUseCase.Params, MealDetail>() {
    
    data class Params(val mealId: String)
    
    override suspend fun execute(params: Params): DataState<MealDetail> {
        return try {
            val meal = repository.getMealById(params.mealId)
            if (meal != null) {
                DataState.Success(meal)
            } else {
                DataState.Error("Meal not found with ID: ${params.mealId}")
            }
        } catch (e: Exception) {
            DataState.Error(e.message ?: "Unknown error occurred")
        }
    }
}


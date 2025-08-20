package com.codingwithyazan.mealapp.domain.meal

import com.codingwithyazan.mealapp.domain.core.BaseUseCase
import com.codingwithyazan.mealapp.domain.core.DataState

class GetMealsByCategoryUseCase(
    private val repository: MealRepository
) : BaseUseCase<GetMealsByCategoryUseCase.Params, List<Meal>>() {
    
    data class Params(val category: String)
    
    override suspend fun execute(params: Params): DataState<List<Meal>> {
        val meals = repository.getMealsByCategory(params.category)
        
        // Log the use case execution for debugging
        println("UseCase: Executing for category: ${params.category}")
        println("UseCase: Got ${meals.size} meals from repository")
        
        return DataState.Success(meals)
    }
}


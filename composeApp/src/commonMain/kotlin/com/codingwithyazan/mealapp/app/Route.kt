package com.codingwithyazan.mealapp.app

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation {
    
    @Serializable
    data object MealsListing : AppNavigation
    
    @Serializable
    data class MealDetail(val mealId: String) : AppNavigation
}
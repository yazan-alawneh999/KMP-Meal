package com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model

import com.codingwithyazan.mealapp.domain.core.ProgressBarState
import com.codingwithyazan.mealapp.domain.core.ViewState
import com.codingwithyazan.mealapp.domain.meal.Meal

data class MealsListingState(
    val isLoading: ProgressBarState = ProgressBarState.Idle,
    val seafoodMeals: List<Meal> = emptyList(),
    val beefMeals: List<Meal> = emptyList(),
    val selectedTab: Int = 0
) : ViewState


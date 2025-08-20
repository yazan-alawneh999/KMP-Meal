package com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model

import com.codingwithyazan.mealapp.domain.core.ProgressBarState
import com.codingwithyazan.mealapp.domain.core.ViewState
import com.codingwithyazan.mealapp.domain.meal.MealDetail

data class MealDetailState(
    val isLoading: ProgressBarState = ProgressBarState.Idle,
    val meal: MealDetail? = null,
    val errorMessage: String? = null
) : ViewState


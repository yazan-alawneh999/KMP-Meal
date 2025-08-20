package com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model

import com.codingwithyazan.mealapp.domain.core.ViewEvent

sealed class MealDetailEvent : ViewEvent {
    data class OnInitialize(val mealId: String) : MealDetailEvent()
    data object OnRetry : MealDetailEvent()
}


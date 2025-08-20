package com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model

import com.codingwithyazan.mealapp.domain.core.ViewEvent

sealed class MealsListingEvent : ViewEvent {
    data object OnInitialize : MealsListingEvent()
    data class OnTabSelected(val tabIndex: Int) : MealsListingEvent()
    data class OnMealClicked(val mealId: String) : MealsListingEvent()
    data object OnRetry : MealsListingEvent()
    data object OnRefresh : MealsListingEvent()
}


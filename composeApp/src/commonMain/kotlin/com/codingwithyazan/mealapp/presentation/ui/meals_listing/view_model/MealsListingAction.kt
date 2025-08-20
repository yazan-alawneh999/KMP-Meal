package com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model

import com.codingwithyazan.mealapp.domain.core.ViewSingleAction


sealed class MealsListingAction : ViewSingleAction {
    
    sealed class Navigation : MealsListingAction() {
        data class NavigateToMealDetail(val mealId: String) : Navigation()
    }
}


package com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model

import com.codingwithyazan.mealapp.domain.core.ViewSingleAction

sealed class MealDetailAction : ViewSingleAction {
    
    sealed class Navigation : MealDetailAction() {
        data object GoBack : Navigation()
    }
}

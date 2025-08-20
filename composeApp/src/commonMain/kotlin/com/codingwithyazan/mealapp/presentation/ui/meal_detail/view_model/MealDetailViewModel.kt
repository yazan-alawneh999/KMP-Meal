package com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model

import androidx.lifecycle.viewModelScope
import com.codingwithyazan.mealapp.domain.core.BaseViewModel
import com.codingwithyazan.mealapp.domain.core.DataState
import com.codingwithyazan.mealapp.domain.core.ProgressBarState
import com.codingwithyazan.mealapp.domain.meal.GetMealByIdUseCase
import com.codingwithyazan.mealapp.domain.meal.MealDetail
import kotlinx.coroutines.launch

class MealDetailViewModel(
    private val getMealByIdUseCase: GetMealByIdUseCase
) : BaseViewModel<MealDetailEvent, MealDetailState, MealDetailAction>() {

    override fun setInitialState(): MealDetailState = MealDetailState()

    override fun onTriggerEvent(event: MealDetailEvent) {
        when (event) {
            is MealDetailEvent.OnInitialize -> {
                loadMealDetail(event.mealId)
            }
            is MealDetailEvent.OnRetry -> {
                // Retry with the last loaded meal ID
                state.value.meal?.idMeal?.let { mealId ->
                    loadMealDetail(mealId)
                }
            }
        }
    }

    private fun loadMealDetail(mealId: String) {
        setState { copy(isLoading = ProgressBarState.FullScreenLoading, errorMessage = null) }
        
        viewModelScope.launch {
            try {
                getMealByIdUseCase.executeAsFlow(GetMealByIdUseCase.Params(mealId))
                    .collect { dataState ->
                        when (dataState) {
                            is DataState.Success -> {
                                setState {
                                    copy(
                                        meal = dataState.data,
                                        isLoading = ProgressBarState.Idle
                                    )
                                }
                            }
                            is DataState.Error -> {
                                setState {
                                    copy(
                                        errorMessage = dataState.message,
                                        isLoading = ProgressBarState.Idle
                                    )
                                }
                            }
                            is DataState.Loading -> {
                                setState { copy(isLoading = dataState.progressBarState) }
                            }
                        }
                    }
            } catch (e: Exception) {
                setState {
                    copy(
                        errorMessage = e.message ?: "Unknown error occurred",
                        isLoading = ProgressBarState.Idle
                    )
                }
            }
        }
    }
}
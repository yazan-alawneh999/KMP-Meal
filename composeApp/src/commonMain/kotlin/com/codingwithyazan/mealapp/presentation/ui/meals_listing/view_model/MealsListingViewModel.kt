package com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model

import androidx.lifecycle.viewModelScope
import com.codingwithyazan.mealapp.domain.core.BaseViewModel
import com.codingwithyazan.mealapp.domain.core.DataState
import com.codingwithyazan.mealapp.domain.core.ProgressBarState
import com.codingwithyazan.mealapp.domain.core.UIComponent
import com.codingwithyazan.mealapp.domain.meal.GetMealsByCategoryUseCase
import com.codingwithyazan.mealapp.domain.meal.Meal
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MealsListingViewModel(
    private val getSeafoodMealsUseCase: GetMealsByCategoryUseCase,
    private val getBeefMealsUseCase: GetMealsByCategoryUseCase
) : BaseViewModel<MealsListingEvent, MealsListingState, MealsListingAction>() {

    override fun setInitialState(): MealsListingState = MealsListingState()
init {
    loadMeals()
}
    override fun onTriggerEvent(event: MealsListingEvent) {
        when (event) {
            is MealsListingEvent.OnInitialize -> {
                loadMeals()
            }
            is MealsListingEvent.OnTabSelected -> {
                println("DEBUG ViewModel: Tab selected: ${event.tabIndex}")
                setState { copy(selectedTab = event.tabIndex) }
                println("DEBUG ViewModel: State updated, selectedTab is now: ${state.value.selectedTab}")
                println("DEBUG ViewModel: Current state - seafood: ${state.value.seafoodMeals.size}, beef: ${state.value.beefMeals.size}")
                // Don't reload meals - just switch tabs
            }
            is MealsListingEvent.OnMealClicked -> {
                setAction { MealsListingAction.Navigation.NavigateToMealDetail(event.mealId) }
            }
            is MealsListingEvent.OnRetry -> {
                println("DEBUG ViewModel: Retry requested, reloading meals")
                // Force reload by clearing current data
                setState { 
                    copy(
                        seafoodMeals = emptyList(),
                        beefMeals = emptyList()
                    )
                }
                loadMeals()
            }
            is MealsListingEvent.OnRefresh -> {
                println("DEBUG ViewModel: Refresh requested, reloading meals")
                // Force reload by clearing current data
                setState { 
                    copy(
                        seafoodMeals = emptyList(),
                        beefMeals = emptyList()
                    )
                }
                loadMeals()
            }
        }
    }

    private fun loadMeals() {
        println("DEBUG ViewModel: loadMeals called - current state: seafood=${state.value.seafoodMeals.size}, beef=${state.value.beefMeals.size}")
        
        // Only load if we don't have data already
        if (state.value.seafoodMeals.isNotEmpty() && state.value.beefMeals.isNotEmpty()) {
            println("DEBUG ViewModel: Data already loaded, skipping API calls")
            return
        }
        
        println("DEBUG ViewModel: Starting to load meals...")
        setState { copy(isLoading = ProgressBarState.FullScreenLoading) }
        
        viewModelScope.launch {
            try {
                println("DEBUG ViewModel: Starting parallel API calls...")
                
                // Load both meals in parallel using coroutines
                val seafoodDeferred = async {
                    getSeafoodMealsUseCase.executeAsFlow(GetMealsByCategoryUseCase.Params("Seafood"))
                        .collect { result ->
                            when (result) {
                                is DataState.Success -> {
                                    val seafoodMeals = result.data ?: emptyList()
                                    println("DEBUG: Seafood API successful: ${seafoodMeals.size}")
                                    setState { copy(seafoodMeals = seafoodMeals) }
                                }
                                is DataState.Error -> {
                                    println("DEBUG: Seafood API error: ${result.message}")
                                    setError {
                                        UIComponent.DialogSimple(
                                            title = "Error Loading Seafood Meals",
                                            description = result.message ?: "Failed to load seafood meals"
                                        )
                                    }
                                }
                                is DataState.Loading -> {
                                    println("DEBUG: Seafood API loading...")
                                }
                            }
                        }
                }
                
                val beefDeferred = async {
                    getBeefMealsUseCase.executeAsFlow(GetMealsByCategoryUseCase.Params("Beef"))
                        .collect { result ->
                            when (result) {
                                is DataState.Success -> {
                                    val beefMeals = result.data ?: emptyList()
                                    println("DEBUG: Beef API successful: ${beefMeals.size}")
                                    setState { copy(beefMeals = beefMeals) }
                                }
                                is DataState.Error -> {
                                    println("DEBUG: Beef API error: ${result.message}")
                                    setError {
                                        UIComponent.DialogSimple(
                                            title = "Error Loading Beef Meals",
                                            description = result.message ?: "Failed to load beef meals"
                                        )
                                    }
                                }
                                is DataState.Loading -> {
                                    println("DEBUG: Beef API loading...")

                                }
                            }
                        }
                }
                
                println("DEBUG ViewModel: Waiting for both APIs to complete...")
                // Wait for both to complete
                seafoodDeferred.await()
                beefDeferred.await()
                
                println("DEBUG ViewModel: Both APIs completed, setting loading to idle")
                // Set loading to idle after both complete
                setState { copy(isLoading = ProgressBarState.Idle) }
                
            } catch (e: Exception) {
                println("DEBUG ViewModel: Exception in loadMeals: ${e.message}")
                setState { copy(isLoading = ProgressBarState.Idle) }
                setError {
                    UIComponent.DialogSimple(
                        title = "Network Error",
                        description = e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }
}
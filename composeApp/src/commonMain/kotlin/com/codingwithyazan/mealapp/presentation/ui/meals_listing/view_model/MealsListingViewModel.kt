package com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model

import androidx.lifecycle.viewModelScope
import com.codingwithyazan.mealapp.domain.core.BaseViewModel
import com.codingwithyazan.mealapp.domain.core.DataState
import com.codingwithyazan.mealapp.domain.core.ProgressBarState
import com.codingwithyazan.mealapp.domain.core.UIComponent
import com.codingwithyazan.mealapp.domain.meal.GetMealsByCategoryUseCase
import com.codingwithyazan.mealapp.domain.meal.Meal
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

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
                setState { copy(selectedTab = event.tabIndex) }
            }
            is MealsListingEvent.OnMealClicked -> {
                setAction { MealsListingAction.Navigation.NavigateToMealDetail(event.mealId) }
            }
            is MealsListingEvent.OnRetry -> {
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
        // Only load if we don't have data already
        if (state.value.seafoodMeals.isNotEmpty() && state.value.beefMeals.isNotEmpty()) {
            return
        }
        
        setState { copy(isLoading = ProgressBarState.FullScreenLoading) }
        
        // Load seafood meals
        loadSeafoodMeals()
        
        // Load beef meals in separate function
        loadBeefMeals()
        
        // Set loading to idle after a delay to allow both coroutines to complete
        viewModelScope.launch {
            kotlinx.coroutines.delay(1000) // Give time for both API calls to complete
            setState { copy(isLoading = ProgressBarState.Idle) }
        }
    }
    
    private fun loadSeafoodMeals() {
        viewModelScope.launch {
            try {
                println("ViewModel: Starting to load seafood meals...")
                val seafoodResult = getSeafoodMealsUseCase.execute(GetMealsByCategoryUseCase.Params("Seafood"))
                println("ViewModel: Seafood result type: ${seafoodResult::class.simpleName}")

                when (seafoodResult) {
                    is DataState.Success -> {
                        val seafoodMeals = seafoodResult.data ?: emptyList()
                        println("ViewModel: Seafood API successful, got ${seafoodMeals.size} meals")
                        if (seafoodMeals.isNotEmpty()) {
                            println("ViewModel: First seafood meal: ${seafoodMeals.first().safeStrMeal()}")
                        }
                        setState { copy(seafoodMeals = seafoodMeals) }
                        println("ViewModel: State updated, seafood meals count: ${state.value.seafoodMeals.size}")
                    }
                    is DataState.Error -> {
                        setError {
                            UIComponent.DialogSimple(
                                title = "Error Loading Seafood Meals",
                                description = seafoodResult.message ?: "Failed to load seafood meals"
                            )
                        }
                    }
                    is DataState.Loading -> {
                        println("ViewModel: Seafood API loading...")
                    }
                }
            } catch (e: Exception) {
                println("ViewModel: Seafood API exception: ${e.message}")
                e.printStackTrace()
                setError {
                    UIComponent.DialogSimple(
                        title = "Error Loading Seafood Meals",
                        description = e.message ?: "Failed to load seafood meals"
                    )
                }
            }
        }
    }
    
    private fun loadBeefMeals() {
        viewModelScope.launch {
            try {
                println("ViewModel: Starting to load beef meals...")
                val beefResult = getBeefMealsUseCase.execute(GetMealsByCategoryUseCase.Params("Beef"))
                println("ViewModel: Beef result type: ${beefResult::class.simpleName}")
                
                when (beefResult) {
                    is DataState.Success -> {
                        val beefMeals = beefResult.data ?: emptyList()
                        println("ViewModel: Beef API successful, got ${beefMeals.size} meals")
                        if (beefMeals.isNotEmpty()) {
                            println("ViewModel: First beef meal: ${beefMeals.first().safeStrMeal()}")
                        }
                        setState { copy(beefMeals = beefMeals) }
                        println("ViewModel: State updated, beef meals count: ${state.value.beefMeals.size}")
                    }
                    is DataState.Error -> {
                        setError {
                            UIComponent.DialogSimple(
                                title = "Error Loading Beef Meals",
                                description = beefResult.message ?: "Failed to load beef meals"
                            )
                        }
                    }
                    is DataState.Loading -> {
                        println("ViewModel: Beef API loading...")
                    }
                }
            } catch (e: Exception) {
                println("ViewModel: Beef API exception: ${e.message}")
                e.printStackTrace()
                setError {
                    UIComponent.DialogSimple(
                        title = "Error Loading Beef Meals",
                        description = e.message ?: "Failed to load beef meals"
                    )
                }
            }
        }
    }
}
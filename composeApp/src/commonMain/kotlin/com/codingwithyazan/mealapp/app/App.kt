package com.codingwithyazan.mealapp.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingwithyazan.mealapp.presentation.theme.MealAppTheme
import com.codingwithyazan.mealapp.presentation.ui.meal_detail.MealDetailScreen
import com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model.MealDetailAction
import com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model.MealDetailEvent
import com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model.MealDetailViewModel
import com.codingwithyazan.mealapp.presentation.ui.meals_listing.MealsListingScreen
import com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model.MealsListingAction
import com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model.MealsListingEvent
import com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model.MealsListingViewModel
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MealAppTheme {
        val navController = rememberNavController()
        MealAppNavigation(navController = navController)
    }
}

@Composable
fun MealAppNavigation(navController: NavHostController) {
    val mealsListingViewModel: MealsListingViewModel = koinViewModel()
    val mealDetailViewModel: MealDetailViewModel = koinViewModel()
    
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = AppNavigation.MealsListing,
            modifier = Modifier.fillMaxSize()
        ) {
                        composable<AppNavigation.MealsListing> {

                
                MealsListingScreen(
                    state = mealsListingViewModel.state.value,
                    events = { event -> mealsListingViewModel.setEvent(event) },
                    errors = mealsListingViewModel.errors
                )
            }
            
            composable<AppNavigation.MealDetail> { backStackEntry ->
                val mealId = backStackEntry.arguments?.getString("mealId") ?: ""

                
                MealDetailScreen(
                    mealId = mealId,
                    state = mealDetailViewModel.state.value,
                    events = { event -> mealDetailViewModel.setEvent(event) },
                    errors = mealDetailViewModel.errors
                )
            }
        }
    }
    
    // Handle navigation actions from MealsListingViewModel
    LaunchedEffect(mealsListingViewModel) {
        mealsListingViewModel.action.onEach { action ->
            when (action) {
                is MealsListingAction.Navigation.NavigateToMealDetail -> {
                    navController.navigate(AppNavigation.MealDetail(action.mealId))
                }
            }
        }.collect {  }
    }
    
    // Handle navigation actions from MealDetailViewModel
    LaunchedEffect(mealDetailViewModel) {
        mealDetailViewModel.action.onEach { action ->
            when (action) {
                is MealDetailAction.Navigation.GoBack -> {
                    navController.popBackStack()
                }
            }
        }.collect {  }
    }
}


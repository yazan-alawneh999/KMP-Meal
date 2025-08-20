package com.codingwithyazan.mealapp.presentation.ui.meals_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_mealapp.composeapp.generated.resources.Res
import cmp_mealapp.composeapp.generated.resources.*

import com.codingwithyazan.mealapp.domain.core.UIComponent
import com.codingwithyazan.mealapp.domain.meal.Meal
import com.codingwithyazan.mealapp.presentation.component.DefaultScreenUI
import com.codingwithyazan.mealapp.presentation.component.MealImage
import com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model.*
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealsListingScreen(
    state: MealsListingState,
    events: (MealsListingEvent) -> Unit,
    errors: Flow<UIComponent>,
) {

    
    // Debug logging
    println("DEBUG UI: selectedTab=${state.selectedTab}, seafood=${state.seafoodMeals.size}, beef=${state.beefMeals.size}")
    
    // Track state changes
    LaunchedEffect(state.selectedTab) {
        println("DEBUG UI: Tab changed to: ${state.selectedTab}")
    }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.isLoading
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Tab Row
            TabRow(
                selectedTabIndex = state.selectedTab,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = state.selectedTab == 0,
                    onClick = { 
                        println("DEBUG UI: Seafood tab clicked")
                        events(MealsListingEvent.OnTabSelected(0)) 
                    },
                    text = { Text(stringResource(Res.string.seafood)) }
                )
                Tab(
                    selected = state.selectedTab == 1,
                    onClick = { 
                        println("DEBUG UI: Beef tab clicked")
                        events(MealsListingEvent.OnTabSelected(1)) 
                    },
                    text = { Text(stringResource(Res.string.beef)) }
                )
            }

            // Content
            when {
                state.selectedTab == 0 -> {
                    println("DEBUG UI: Showing seafood tab with ${state.seafoodMeals.size} meals")
                    MealsList(
                        meals = state.seafoodMeals,
                        onMealClicked = { mealId -> events(MealsListingEvent.OnMealClicked(mealId)) }
                    )
                }
                state.selectedTab == 1 -> {
                    println("DEBUG UI: Showing beef tab with ${state.beefMeals.size} meals")
                    MealsList(
                        meals = state.beefMeals,
                        onMealClicked = { mealId -> events(MealsListingEvent.OnMealClicked(mealId)) }
                    )
                }
                else -> {
                    // Default case - show seafood
                    println("DEBUG UI: Default case - showing seafood")
                    MealsList(
                        meals = state.seafoodMeals,
                        onMealClicked = { mealId -> events(MealsListingEvent.OnMealClicked(mealId)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun MealsList(
    meals: List<Meal>,
    onMealClicked: (String) -> Unit
) {
    println("DEBUG MealsList: received ${meals.size} meals")
    if (meals.isEmpty()) {
        println("DEBUG MealsList: showing no meals message")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(Res.string.no_meals_available),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        println("DEBUG MealsList: showing ${meals.size} meals in LazyColumn")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(meals) { meal ->
                MealCard(
                    meal = meal,
                    onClick = { onMealClicked(meal.idMeal) }
                )
            }
        }
    }
}

@Composable
private fun MealCard(
    meal: Meal,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Meal Image
            MealImage(
                url = meal.strMealThumb,
                contentDescription = stringResource(Res.string.meal_image_for, meal.strMeal),
                modifier = Modifier
                    .width(120.dp)
                    .height(80.dp)
                    .padding(end = 16.dp)
            )
            
            // Meal Details Column
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Meal Name
                Text(
                    text = meal.strMeal,
                    style = MaterialTheme.typography.titleMedium
                )
                
                // Removed debug URL display
            }
        }
    }
}





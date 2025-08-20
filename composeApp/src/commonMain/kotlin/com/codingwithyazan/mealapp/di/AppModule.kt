package com.codingwithyazan.mealapp.di

import com.codingwithyazan.mealapp.data.repository.MealRepositoryImpl
import com.codingwithyazan.mealapp.domain.meal.GetMealByIdUseCase
import com.codingwithyazan.mealapp.domain.meal.GetMealsByCategoryUseCase
import com.codingwithyazan.mealapp.domain.meal.MealRepository
import com.codingwithyazan.mealapp.presentation.ui.meal_detail.view_model.MealDetailViewModel
import com.codingwithyazan.mealapp.presentation.ui.meals_listing.view_model.MealsListingViewModel
import org.koin.dsl.module

val appModule = module {
    
    // Repository
    single<MealRepository> { 
        MealRepositoryImpl(get())
    }
    
    // Use Cases
    single<GetMealsByCategoryUseCase> { 
        GetMealsByCategoryUseCase(get())
    }
    
    single<GetMealByIdUseCase> { 
        GetMealByIdUseCase(get())
    }
    
    // ViewModels
    single<MealsListingViewModel> { 
        MealsListingViewModel(get(), get())
    }
    
    single<MealDetailViewModel> { 
        MealDetailViewModel(get())
    }
}

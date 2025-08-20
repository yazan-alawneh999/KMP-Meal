package com.codingwithyazan.mealapp.di

import com.codingwithyazan.mealapp.data.remote.MealApiService
import org.koin.dsl.module

val androidModule = module {
    
    // Platform-specific implementations
    single<MealApiService> { 
        PlatformModule.createMealApiService(PlatformModule.createRetrofit())
    }
}

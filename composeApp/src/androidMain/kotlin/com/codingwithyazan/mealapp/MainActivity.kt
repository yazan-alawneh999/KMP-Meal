package com.codingwithyazan.mealapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.codingwithyazan.mealapp.app.App
import com.codingwithyazan.mealapp.di.appModule
import com.codingwithyazan.mealapp.di.androidModule
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Koin
        startKoin {
            modules(listOf(appModule, androidModule))
        }

        setContent {
            App()
        }
    }
}
package com.codingwithyazan.mealapp

import androidx.compose.ui.window.ComposeUIViewController
import com.codingwithyazan.mealapp.app.App
import com.codingwithyazan.mealapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
package com.codingwithyazan.mealapp.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(listOf(appModule, iosModule))
    }
}

package com.app.coresamplesourcemvvmkoinkotlin.di

import com.app.coresamplesourcemvvmkoinkotlin.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { MainViewModel(get()) }
}
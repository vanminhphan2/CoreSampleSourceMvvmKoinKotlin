package com.app.coresamplesourcemvvmkoinkotlin

import android.app.Application
import com.app.coresamplesourcemvvmkoinkotlin.di.appModule
import com.app.coresamplesourcemvvmkoinkotlin.di.networkModule
import com.app.coresamplesourcemvvmkoinkotlin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


open class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(appModule, networkModule, viewModelModule))
        }
    }
}
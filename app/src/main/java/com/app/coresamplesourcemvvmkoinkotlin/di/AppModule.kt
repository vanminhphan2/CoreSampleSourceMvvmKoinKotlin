package com.app.coresamplesourcemvvmkoinkotlin.di

import androidx.room.Room
import com.app.coresamplesourcemvvmkoinkotlin.data.local.prefs.PreferencesHelper
import com.app.coresamplesourcemvvmkoinkotlin.data.local.room.Database
import com.app.coresamplesourcemvvmkoinkotlin.utils.AppConstants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {
    single { PreferencesHelper(androidContext(), AppConstants.PREFERENCE_NAME) }

    single {
        Room.databaseBuilder(get(), Database::class.java, AppConstants.ROOM_NAME).fallbackToDestructiveMigration()
            .build()
    }
    single { get<Database>().userDao() }
}
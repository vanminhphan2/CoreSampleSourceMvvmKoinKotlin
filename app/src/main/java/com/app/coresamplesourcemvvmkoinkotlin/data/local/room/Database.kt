package com.app.coresamplesourcemvvmkoinkotlin.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.coresamplesourcemvvmkoinkotlin.data.pojos.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}

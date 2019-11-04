package com.app.coresamplesourcemvvmkoinkotlin.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.coresamplesourcemvvmkoinkotlin.data.pojos.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long
    @Query("SELECT * FROM users WHERE id IN (:id)")
    fun findUserById(id: Int?): User
}
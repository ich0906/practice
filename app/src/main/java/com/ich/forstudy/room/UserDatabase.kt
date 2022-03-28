package com.ich.forstudy.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao

    companion object{
        const val DATABASE_NAME = "user_db"
    }
}
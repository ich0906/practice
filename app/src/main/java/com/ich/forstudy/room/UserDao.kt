package com.ich.forstudy.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE age = :stdAge")
    fun loadUsersWithAge(stdAge: Int): Flow<List<User>>

    @Query("SELECT * FROM user WHERE region IN (:regions)")
    fun loadUsersFromRegions(regions: List<String>): Flow<List<User>>
}
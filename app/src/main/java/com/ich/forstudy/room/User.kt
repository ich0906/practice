package com.ich.forstudy.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var id: Int,

    val firstName: String,
    val lastName: String,
    val age: Int,
    val region: String
)
package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(indices = [
    Index(value = ["email"], unique = true),
    Index(value = ["user_name"], unique = true)
])
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password_hash") val passwordHash: String,
    val email: String,
    @ColumnInfo(name = "email_confirmed") val emailConfirmed: Boolean = false,
    @ColumnInfo(name = "is_logged_in") val isLoggedIn: Boolean = false
)

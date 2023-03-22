package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(primaryKeys = ["user_id", "league_id"],
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = CASCADE),
        ForeignKey(entity = League::class,
            parentColumns = arrayOf("id") ,
            childColumns = arrayOf("league_id"),
            onDelete = CASCADE)]
)
data class UserLeague(
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "league_id") val leagueId: Int,
    val points: Int,
    val admin: Boolean = false
)

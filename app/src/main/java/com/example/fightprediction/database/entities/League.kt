package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
    Index(value = ["league_code"], unique = true)
])
data class League(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    @ColumnInfo(name = "league_code") val leagueCode: String,
    @ColumnInfo(name = "end_date") val endDate: String
)


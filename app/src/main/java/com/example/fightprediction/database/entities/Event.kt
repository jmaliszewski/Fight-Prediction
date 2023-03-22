package com.example.fightprediction.database.entities

import androidx.room.*

@Entity(indices = [
    Index(value = ["name", "date"], unique = true)
])
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val date: String,
    val city: String,
    @ColumnInfo(name = "count_of_fights") val countOfFights: Int

)

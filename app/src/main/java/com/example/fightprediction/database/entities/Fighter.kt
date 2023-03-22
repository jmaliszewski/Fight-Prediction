package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fighter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val weight: Double,
    val height: Double,
    val age: Int,
    val nationality: String,
    @ColumnInfo(name = "wins_by_ko") val winsByKO: Int = 0,
    @ColumnInfo(name = "wins_by_decision") val winsByDecision: Int = 0,
    @ColumnInfo(name = "wins_by_submission") val winsBySubmission: Int = 0,
    @ColumnInfo(name = "loses_by_ko") val losesByKO: Int = 0,
    @ColumnInfo(name = "loses_by_decision") val losesByDecision: Int = 0,
    @ColumnInfo(name = "loses_by_submission") val losesBySubmission: Int = 0,
    val draws: Int = 0,
    val club: String?,
    @ColumnInfo(name = "rank_number") val rankNumber: Int?
    )

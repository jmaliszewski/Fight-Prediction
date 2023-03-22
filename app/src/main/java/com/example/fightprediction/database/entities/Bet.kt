package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = User::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("user_id"),
    onDelete = CASCADE),
    ForeignKey(entity = League::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("league_id"),
    onDelete = CASCADE),
    ForeignKey(entity = Fight::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("fight_id"),
    onDelete = CASCADE),
    ForeignKey(entity = Fighter::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("fighter_id"),
    onDelete = CASCADE)
])
data class Bet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "finish_round") val finishRound: Int,
    @ColumnInfo(name = "finish_method") val finishMethod: String,
    val points: Int,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "league_id") val leagueId: Int,
    @ColumnInfo(name = "fight_id") val fightId: Int,
    @ColumnInfo(name = "fighter_id") val fighterId: Int
)

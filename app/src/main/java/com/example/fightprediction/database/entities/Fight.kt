package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Event::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("event_id"),
    onDelete = CASCADE),
    ForeignKey(entity = Fighter::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("first_fighter_id"),
    onDelete = CASCADE),
    ForeignKey(entity = Fighter::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("second_fighter_id"),
    onDelete = CASCADE)
])
data class Fight(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val result: Int?,
    @ColumnInfo(name = "finish_method") val finishMethod: String?,
    @ColumnInfo(name = "finish_round") val finishRound: Int?,
    @ColumnInfo(name = "finish_time") val finishTime: String?,
    @ColumnInfo(name = "fight_number") val fightNumber: Int?,
    @ColumnInfo(name = "event_id") val eventId: Int,
    @ColumnInfo(name = "first_fighter_id") val firstFighterId: Int,
    @ColumnInfo(name = "second_fighter_id") val secondFighterId: Int,
    @ColumnInfo(name = "weight_category") val weightCategory: Double?,
    @ColumnInfo(name = "title_fight") val fightType: Int
)

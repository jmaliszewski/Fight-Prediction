package com.example.fightprediction.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["event_id", "league_id"],
    foreignKeys = [
        ForeignKey(entity = Event::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("event_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = League::class,
            parentColumns = arrayOf("id") ,
            childColumns = arrayOf("league_id"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class EventLeague(
    @ColumnInfo(name = "event_id") val eventId: Int,
    @ColumnInfo(name = "league_id") val leagueId: Int
)

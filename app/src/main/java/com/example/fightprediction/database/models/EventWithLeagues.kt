package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fightprediction.database.entities.Event
import com.example.fightprediction.database.entities.EventLeague
import com.example.fightprediction.database.entities.League

data class EventWithLeagues(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "event_id",
        entityColumn = "league_id",
        associateBy = Junction(EventLeague::class)
    )
    val leagues: List<League>
)

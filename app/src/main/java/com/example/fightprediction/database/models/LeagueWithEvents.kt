package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fightprediction.database.entities.Event
import com.example.fightprediction.database.entities.EventLeague
import com.example.fightprediction.database.entities.League

data class LeagueWithEvents(
    @Embedded val league: League,
    @Relation(
        parentColumn = "league_id",
        entityColumn = "event_id",
        associateBy = Junction(EventLeague::class)
    )
    val events: List<Event>
)

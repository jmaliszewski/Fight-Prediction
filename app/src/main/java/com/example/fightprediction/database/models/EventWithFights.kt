package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fightprediction.database.entities.Event
import com.example.fightprediction.database.entities.Fight

data class EventWithFights(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "id",
        entityColumn = "event_id"
    )
    val fights: List<Fight>
)

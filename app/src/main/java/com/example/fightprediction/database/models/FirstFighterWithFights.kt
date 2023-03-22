package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fightprediction.database.entities.Fight
import com.example.fightprediction.database.entities.Fighter

data class FirstFighterWithFights(
    @Embedded val fighter: Fighter,
    @Relation(
        parentColumn = "id",
        entityColumn = "first_fighter_id"
    )
    val fights: List<Fight>
)

package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fightprediction.database.entities.Bet
import com.example.fightprediction.database.entities.Fight

data class FighterWithBets(
    @Embedded val fighter: Fight,
    @Relation(
        parentColumn = "id",
        entityColumn = "fighter_id"
    )
    val bets: List<Bet>
)

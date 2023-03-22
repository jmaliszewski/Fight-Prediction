package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fightprediction.database.entities.Bet
import com.example.fightprediction.database.entities.Fight

data class FightWithBets(
    @Embedded val fight: Fight,
    @Relation(
        parentColumn = "id",
        entityColumn = "fight_id"
    )
    val bets: List<Bet>
)

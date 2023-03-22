package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.fightprediction.database.entities.Bet
import com.example.fightprediction.database.entities.League

data class LeagueWithBets(
    @Embedded val league: League,
    @Relation(
        parentColumn = "id",
        entityColumn = "league_id"
    )
    val bets: List<Bet>
)

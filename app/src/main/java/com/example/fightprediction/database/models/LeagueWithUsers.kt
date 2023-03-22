package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fightprediction.database.entities.League
import com.example.fightprediction.database.entities.User
import com.example.fightprediction.database.entities.UserLeague

data class LeagueWithUsers(
    @Embedded val league: League,
    @Relation(
        parentColumn = "league_id",
        entityColumn = "user_id",
        associateBy = Junction(UserLeague::class)
    )
    val users: List<User>
)

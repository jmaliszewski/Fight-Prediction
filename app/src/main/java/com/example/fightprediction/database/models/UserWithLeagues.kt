package com.example.fightprediction.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.fightprediction.database.entities.League
import com.example.fightprediction.database.entities.User
import com.example.fightprediction.database.entities.UserLeague

data class UserWithLeagues(
    @Embedded val user: User,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "league_id",
        associateBy = Junction(UserLeague::class)
    )
    val leagues: List<League>
)

package com.example.fightprediction.models

import com.example.fightprediction.database.entities.Fight
import com.example.fightprediction.database.entities.Fighter

data class FightDetails(
    val fight: Fight,
    val firstFighter: Fighter,
    val secondFighter: Fighter
)

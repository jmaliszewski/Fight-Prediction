package com.example.fightprediction.api.responseModels

import com.example.fightprediction.database.entities.*

data class ApiResponse (
    val user: User,
    val leagues: List<League>,
    val events: List<Event>,
    val fights: List<Fight>,
    val fighters: List<Fighter>,
    val bets: List<Bet>,
    val userLeagues: List<UserLeague>,
    val eventLeagues: List<EventLeague>
)

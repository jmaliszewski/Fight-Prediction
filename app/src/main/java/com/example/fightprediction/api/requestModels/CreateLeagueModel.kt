package com.example.fightprediction.api.requestModels

data class CreateLeagueModel (
    val name: String,
    val type: Int,
    val leagueCode: String,
    val endDate: String,
    val userId: String
)
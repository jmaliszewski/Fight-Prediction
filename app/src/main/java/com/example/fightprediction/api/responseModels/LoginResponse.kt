package com.example.fightprediction.api.responseModels

data class LoginResponse(
    val token: String,
    val expiration: String
)

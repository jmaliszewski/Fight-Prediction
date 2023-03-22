package com.example.fightprediction.api

data class ResponseModel<T> (
    val succeeded: Boolean,
    val data: T,
    val message: String?
)

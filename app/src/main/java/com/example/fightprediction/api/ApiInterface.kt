package com.example.fightprediction.api

import com.example.fightprediction.common.ApiConstants
import com.example.fightprediction.api.requestModels.CreateLeagueModel
import com.example.fightprediction.api.requestModels.LoginModel
import com.example.fightprediction.api.requestModels.RegisterModel
import com.example.fightprediction.api.responseModels.ApiResponse
import com.example.fightprediction.api.responseModels.LoginResponse
import com.example.fightprediction.api.responseModels.RegisterResponse
import com.example.fightprediction.database.entities.League
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    companion object{
        const val BASE_URL = "http://46.41.150.237:8000/api/"
    }

    @POST(ApiConstants.LOGIN)
    @Headers("Content-Type:application/json")
    suspend fun login(
        @Body loginModel: LoginModel
    ): Response<ResponseModel<LoginResponse>>

    @Headers("Content-Type:application/json")
    @POST(ApiConstants.REGISTER)
    suspend fun register(
        @Body registerModel: RegisterModel
    ): Response<ResponseModel<RegisterResponse>>

    @POST(ApiConstants.RESET_PASSWORD)
    @Headers("Content-Type:application/json")
    suspend fun resetPassword(
        @Body email: String
    ): Response<ResponseModel<String>>

    @POST(ApiConstants.CREATE_LEAGUE)
    suspend fun createLeague(
        @Body createLeagueModel: CreateLeagueModel
    ) : Response<ResponseModel<League>>


    @Headers("Content-Type:application/json")
    @GET(ApiConstants.GET_DATA)
    suspend fun getData(
        @Header("Authorization") authToken: String
    ) : Response<ResponseModel<ApiResponse>>
}
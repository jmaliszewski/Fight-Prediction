package com.example.fightprediction.login_and_registration

import android.util.Log
import androidx.lifecycle.*
import androidx.room.withTransaction
import com.example.fightprediction.api.ApiInterface
import com.example.fightprediction.api.ResponseModel
import com.example.fightprediction.api.requestModels.LoginModel
import com.example.fightprediction.api.responseModels.ApiResponse
import com.example.fightprediction.common.ApiConstants
import com.example.fightprediction.common.SharedPreferences
import com.example.fightprediction.database.AppDatabase
import com.example.fightprediction.repository.*
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val leagueRepository: LeagueRepository,
    private val eventRepository: EventRepository,
    private val fightRepository: FightRepository,
    private val fighterRepository: FighterRepository,
    private val betRepository: BetRepository,
    private val state: SavedStateHandle,
    private val appDatabase: Provider<AppDatabase>,
    private val sharedPreferences: SharedPreferences
) : ViewModel(){

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    var userName = state.get<String>("userName") ?: ""
        set(value) {
            field = value
            state["userName"] = value
        }

    var password = state.get<String>("password") ?: ""
        set(value) {
            field = value
            state["password"] = value
        }

    fun resetPassword() = viewModelScope.launch {
        loginEventChannel.send(LoginEvent.NavigateToResetPasswordFragment)
    }

    fun signUp() = viewModelScope.launch {
        loginEventChannel.send(LoginEvent.NavigateToSignUpFragment)
    }

    fun signIn() = viewModelScope.launch {
        if(userName.isBlank() || password.isBlank())
            loginEventChannel.send(LoginEvent.ShowBadLoginDataMessage("Musisz wpisać login i hasło"))
        else{
            val loginModel = LoginModel(userName, password)
            userRepository.getDataForUser(loginModel).let { response ->
                if(response.isSuccessful){
                    val responseModel = response.body()?.data
                    sharedPreferences.setStoredTag(ApiConstants.AUTH_TOKEN_KEY, responseModel?.token.orEmpty())
                    sharedPreferences.setStoredTag(ApiConstants.EXPIRATION_AUTH_TOKEN_KEY, responseModel?.expiration.orEmpty())
                    getData(responseModel?.token.orEmpty())
                } else{
                    loginEventChannel.send(LoginEvent.ShowBadLoginDataMessage(response.message()))
                }
            }
        }
    }

    private suspend fun getData(token: String){
        if(token.isNotBlank()){
            userRepository.getData("Bearer $token").let { response ->
                if(response.isSuccessful){
                    val responseModel = response.body()?.data
                //    Log.e("SIEMA", response.body()?.message.toString())
                    deleteAllObjects()
                    insertAllObjects(responseModel)
                } else{
                    loginEventChannel.send(LoginEvent.ShowBadLoginDataMessage(response.message()))
                }
            }
        } else{
            loginEventChannel.send(LoginEvent.ShowBadLoginDataMessage("Nie udało się pobrać danych"))
        }
    }

    private suspend fun insertAllObjects(apiResponse: ApiResponse?){
        if(apiResponse != null){
            try{
                appDatabase.get().withTransaction {
                    userRepository.insertUser(apiResponse.user)
                    leagueRepository.insertLeagues(apiResponse.leagues)
                    eventRepository.insertEvents(apiResponse.events)
                    fighterRepository.insertFighters(apiResponse.fighters)
                    fightRepository.insertFights(apiResponse.fights)
                    betRepository.insertBets(apiResponse.bets)
                    userRepository.insertUserLeagues(apiResponse.userLeagues)
                    leagueRepository.insertLeagueEvents(apiResponse.eventLeagues);

                    loginEventChannel.send(LoginEvent.NavigateToUpcomingEvents)
                }
            } catch (e: Exception){
                loginEventChannel.send(LoginEvent.ShowBadLoginDataMessage("Błąd podczas pobierania danych"))
            }
        }
    }

    private suspend fun deleteAllObjects(){
        try{
            appDatabase.get().withTransaction {
                fighterRepository.deleteAllFighters()
                eventRepository.deleteAllEvents()
                fightRepository.deleteAllFights()
                leagueRepository.deleteAllLeagues()
                userRepository.deleteUsers()
                betRepository.deleteAllBets()

        //        loginEventChannel.send(LoginEvent.NavigateToUpcomingEvents)
            }
        } catch (e: Exception){
            loginEventChannel.send(LoginEvent.ShowBadLoginDataMessage("Błąd podczas pobierania danych"))
        }
    }

    sealed class LoginEvent{
        object NavigateToSignUpFragment : LoginEvent()
        object NavigateToResetPasswordFragment : LoginEvent()
        object NavigateToUpcomingEvents : LoginEvent()
        data class ShowBadLoginDataMessage(val message: String) : LoginEvent()
    }
}
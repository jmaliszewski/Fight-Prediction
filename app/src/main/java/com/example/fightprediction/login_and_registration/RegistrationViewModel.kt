package com.example.fightprediction.login_and_registration

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fightprediction.api.ResponseModel
import com.example.fightprediction.api.requestModels.RegisterModel
import com.example.fightprediction.api.responseModels.RegisterResponse
import com.example.fightprediction.repository.UserRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    private val registrationEventChannel = Channel<RegistrationEvent>()
    val registrationEvent = registrationEventChannel.receiveAsFlow()

    var userName = state.get<String>("userName") ?: ""
        set(value) {
            field = value
            state["userName"] = value
        }

    var email = state.get<String>("email") ?: ""
        set(value) {
            field = value
            state["email"] = value
        }

    var password = state.get<String>("password") ?: ""
        set(value) {
            field = value
            state["password"] = value
        }

    var passwordConfirm = state.get<String>("passwordConfirm") ?: ""
        set(value) {
            field = value
            state["passwordConfirm"] = value
        }

    fun signIn() = viewModelScope.launch {
        registrationEventChannel.send(RegistrationEvent.NavigateToSignInFragment)
    }

    fun signUp() = viewModelScope.launch {
        if(checkFields()){
            if(checkPassword()){
                val registerModel = RegisterModel(email, userName, password)
                userRepository.register(registerModel).let { response ->
                    if(response.isSuccessful){
                       // val responseModel = response.body()?.data //Gson().fromJson(response.body()?.data..toString(), RegisterResponse::class.java)
                        if(response.body()?.succeeded == true){
                            registrationEventChannel.send(RegistrationEvent.NavigateToSignInWithMessage(response.body()?.message.orEmpty()))
                        } else{
                            registrationEventChannel.send(RegistrationEvent.ShowMessage(response.body()?.message.orEmpty()));
                        }
                    } else{
                        val responseError = response.errorBody()?.string()
                        val responseModel = Gson().fromJson(responseError, ResponseModel::class.java)
                        registrationEventChannel.send(RegistrationEvent.ShowMessage(responseModel.message.orEmpty()))
                    }
                }
            } else{
                registrationEventChannel.send(RegistrationEvent.ShowMessage("Hasła nie są identyczne"))
            }
        } else{
            registrationEventChannel.send(RegistrationEvent.ShowMessage("Musisz uzupełnić wszystkie pola"))
        }
    }

    private fun checkFields() : Boolean{
        if(userName.isBlank() || email.isBlank() || password.isBlank() || passwordConfirm.isBlank()){
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean{
        if(password == passwordConfirm)
            return true
        return false
    }

    private fun hashPassword(): String{
        return ""
    }

    sealed class RegistrationEvent(){
        object NavigateToSignInFragment : RegistrationEvent()
        data class NavigateToSignInWithMessage(val message: String) : RegistrationEvent()
        data class ShowMessage(val message: String) : RegistrationEvent()
    }
}
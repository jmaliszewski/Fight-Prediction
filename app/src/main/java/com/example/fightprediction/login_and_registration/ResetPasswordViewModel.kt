package com.example.fightprediction.login_and_registration

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fightprediction.api.ResponseModel
import com.example.fightprediction.repository.UserRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val state: SavedStateHandle
) : ViewModel(){

    private val resetPasswordChannel = Channel<ResetPasswordEvent>()
    val resetPasswordEvent = resetPasswordChannel.receiveAsFlow()

    var email = state.get<String>("email") ?: ""
        set(value) {
            field = value
            state["email"] = value
        }

    fun signIn() = viewModelScope.launch {
        resetPasswordChannel.send(ResetPasswordEvent.NavigateToSignInFragment)
    }

    fun recoverPassword() = viewModelScope.launch {
        if(email.isNotBlank()){
            userRepository.resetPassword(email).let { response ->
                if(response.isSuccessful){
                    val responseModel = Gson().fromJson(response.body().toString(), ResponseModel::class.java)
                } else{
                    //TODO check whether display message, code or errorBody
                    resetPasswordChannel.send(ResetPasswordEvent.ShowMessage(response.message()))
                }
            }
        } else{
            resetPasswordChannel.send(ResetPasswordEvent.ShowMessage("Musisz podać adres e-mail"))
        }

//        //TODO obsługa wysłania requestu i sprawdzenia meila a następnie przekierowanie logowania
//        resetPasswordChannel.send(ResetPasswordEvent.ShowMessageAfterRecoverPassword(
//            "Na Twój adres e-mail został wysłany link umożliwiający zmianę hasła"))
    }

    sealed class ResetPasswordEvent{
        object NavigateToSignInFragment : ResetPasswordEvent()
        data class NavigateToSignInWithMessage(val message: String) : ResetPasswordEvent()
        data class ShowMessage(val message: String) : ResetPasswordEvent()
    }
}
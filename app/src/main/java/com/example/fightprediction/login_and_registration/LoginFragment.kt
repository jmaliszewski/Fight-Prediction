package com.example.fightprediction.login_and_registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fightprediction.R
import com.example.fightprediction.databinding.LoginFragmentBinding
import com.example.fightprediction.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment){
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView(view)
        setEvents()
    }

    private fun bindView(view: View){
        val binding = LoginFragmentBinding.bind(view)

        binding.apply {
            editTextLogin.addTextChangedListener { userName -> viewModel.userName = userName.toString() }
            editTextPassword.addTextChangedListener { password -> viewModel.password = password.toString() }
            textViewResetPassword.setOnClickListener { viewModel.resetPassword() }
            textViewSignUp.setOnClickListener { viewModel.signUp() }
            buttonSignIn.setOnClickListener { viewModel.signIn() }
        }
    }

    private fun setEvents(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginEvent.collect { event ->
                when(event){
                    is LoginViewModel.LoginEvent.ShowBadLoginDataMessage -> {
                        if(event.message.isNotEmpty())
                            Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                        else
                            Snackbar.make(requireView(), "Wystąpił nieoczekiwany błąd, spróbuj ponownie za chwilę", Snackbar.LENGTH_LONG).show()
                    }
                    is LoginViewModel.LoginEvent.NavigateToResetPasswordFragment -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment()
                        findNavController().navigate(action)
                    }
                    is LoginViewModel.LoginEvent.NavigateToSignUpFragment -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
                        findNavController().navigate(action)
                    }
                    is LoginViewModel.LoginEvent.NavigateToUpcomingEvents -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToEventsFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }
}
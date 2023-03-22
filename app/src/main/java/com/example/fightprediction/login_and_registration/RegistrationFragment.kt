package com.example.fightprediction.login_and_registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fightprediction.R
import com.example.fightprediction.databinding.RegistrationFragmentBinding
import com.example.fightprediction.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RegistrationFragment : Fragment(R.layout.registration_fragment) {
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView(view)
        setEvents();
    }

    private fun bindView(view: View){
        val binding = RegistrationFragmentBinding.bind(view)

        binding.apply {
            editTextEmail.addTextChangedListener { email -> viewModel.email = email.toString()  }
            editTextUserName.addTextChangedListener { userName -> viewModel.userName = userName.toString() }
            editTextPassword.addTextChangedListener { password -> viewModel.password = password.toString() }
            editTextPasswordConfirm.addTextChangedListener { passwordConfirm -> viewModel.passwordConfirm = passwordConfirm.toString() }
            textViewSignIn.setOnClickListener { viewModel.signIn() }
            buttonSignUp.setOnClickListener { viewModel.signUp() }
        }
    }

    private fun setEvents(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.registrationEvent.collect{ event ->
                when(event){
                    is RegistrationViewModel.RegistrationEvent.NavigateToSignInFragment -> {
                        val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                    is RegistrationViewModel.RegistrationEvent.ShowMessage -> {
                        if(event.message.isNotEmpty())
                            Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                        else
                            Snackbar.make(requireView(), "Wystąpił nieoczekiwany błąd, spróbuj ponownie za chwilę", Snackbar.LENGTH_LONG).show()
                    }
                    is RegistrationViewModel.RegistrationEvent.NavigateToSignInWithMessage -> {
                        if(event.message.isNotEmpty())
                            Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                        else
                            Snackbar.make(requireView(), "Wystąpił nieoczekiwany błąd, spróbuj ponownie za chwilę", Snackbar.LENGTH_LONG).show()
                        val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }

}
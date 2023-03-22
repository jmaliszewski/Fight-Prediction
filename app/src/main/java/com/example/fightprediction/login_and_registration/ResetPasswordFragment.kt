package com.example.fightprediction.login_and_registration

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fightprediction.R
import com.example.fightprediction.databinding.ResetPasswordFragmentBinding
import com.example.fightprediction.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ResetPasswordFragment : Fragment(R.layout.reset_password_fragment) {
    private val viewModel: ResetPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView(view)
        setEvents()
    }

    private fun bindView(view: View){
        val binding = ResetPasswordFragmentBinding.bind(view)

        binding.apply {
            editTextEmail.addTextChangedListener { email -> viewModel.email = email.toString() }
            textViewSignUp.setOnClickListener { viewModel.signIn() }
            buttonRecoverPassword.setOnClickListener { viewModel.recoverPassword() }
        }
    }

    private fun setEvents(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.resetPasswordEvent.collect { event ->
                when(event){
                    is ResetPasswordViewModel.ResetPasswordEvent.NavigateToSignInFragment -> {
                        val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                    is ResetPasswordViewModel.ResetPasswordEvent.NavigateToSignInWithMessage -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                        val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                    is ResetPasswordViewModel.ResetPasswordEvent.ShowMessage -> {
                        Snackbar.make(requireView(), event.message, Snackbar.LENGTH_LONG).show()
                    }
                }.exhaustive
            }
        }
    }
}
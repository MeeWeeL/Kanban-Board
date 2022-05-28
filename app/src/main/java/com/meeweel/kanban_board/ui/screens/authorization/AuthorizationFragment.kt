package com.meeweel.kanban_board.ui.screens.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentAuthorizationBinding
import com.meeweel.kanban_board.ui.MAIN

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    private val viewModel: AuthorizationViewModel by lazy {
        ViewModelProvider(this).get(AuthorizationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authObserver = Observer<AuthorizationState> { renderAuth(it) }
        val isSignObserver = Observer<Boolean> { renderIsSingIn(it) }

        viewModel.getData().observe(viewLifecycleOwner, authObserver)
        viewModel.isAuth().observe(viewLifecycleOwner, isSignObserver)

        viewModel.checkAuthorization()

        binding.buttonSignIn.setOnClickListener {
            viewModel.signIn(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
        }

        binding.buttonSignUp.setOnClickListener {
            viewModel.signUp(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
            MAIN.navController.navigate(R.id.action_textViewLoginRegistration_to_registrationFragment)
        }
    }

    private fun renderAuth(state: AuthorizationState) {
        when (state) {
            is AuthorizationState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.editTextLogin.setText(state.data.login)
                binding.editTextPassword.setText(state.data.password)
            }
            is AuthorizationState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AuthorizationState.Done -> {
                binding.loadingLayout.visibility = View.GONE
            }
        }
    }
    private fun renderIsSingIn(b: Boolean) {
        if (!b) {
            binding.loadingLayout.visibility = View.GONE
            Toast.makeText(requireContext(), "Authorization failed", Toast.LENGTH_SHORT).show()
        } else {
            MAIN.navController.navigate(R.id.action_authorizationFragment_to_mainScreenFragment)
        }
    }
}
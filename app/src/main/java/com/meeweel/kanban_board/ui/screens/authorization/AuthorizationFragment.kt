package com.meeweel.kanban_board.ui.screens.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentAuthorizationBinding
import com.meeweel.kanban_board.ui.MAIN
import java.util.regex.Pattern

class AuthorizationFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding
        get() {
            return _binding!!
        }

    private val viewModel: AuthorizationViewModel by lazy {
        ViewModelProvider(this).get(AuthorizationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        binding.buttonSignUp.setOnClickListener(this)
        binding.buttonSignIn.setOnClickListener(this)
        initFocusListeners()
    }

    private fun initObserver() {
        val authObserver = Observer<AuthorizationState> { renderAuth(it) }
        val isSignObserver = Observer<Boolean> { renderIsSingIn(it) }

        viewModel.getData().observe(viewLifecycleOwner, authObserver)
        viewModel.isAuth().observe(viewLifecycleOwner, isSignObserver)
        viewModel.checkAuthorization()
    }

    private fun initFocusListeners() {
        binding.editTextLogin.doOnTextChanged { _, _, _, _ ->
            binding.loginLayout.helperText = validLogin()
        }
        binding.editTextPassword.doOnTextChanged { _, _, _, _ ->
            binding.passwordLayout.helperText = validPassword()
        }
    }

    private fun validLogin(): CharSequence? {
        val loginText = binding.editTextLogin.editableText.toString()
        val p = Pattern.compile(WHITESPACE)
        val k = Pattern.compile("[a-zA-Z0-9.\\-_]+")
        if (loginText.isEmpty()) {
            return "Required*"
        }
        if (!p.matcher(loginText).matches()) {
            return "Don't use space"
        }
        if (!k.matcher(loginText).matches()) {
            return "Don't use symbols"
        }
        if (loginText.length < 6) {
            return "Minimum 6 Character Login"
        }
        return null
    }

    private fun validPassword(): CharSequence? {
        val passwordText = binding.editTextPassword.editableText.toString()
        val p = Pattern.compile(WHITESPACE)
        if (passwordText.isEmpty()) {
            return "Required*"
        }
        if (!p.matcher(passwordText).matches()) {
            return "Don't use space"
        }
        if (passwordText.length < 6) {
            return "Minimum 6 Character Password"
        }
        return null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSignUp -> {
                MAIN.navController.navigate(R.id.action_textViewLoginRegistration_to_registrationFragment)
            }
            R.id.buttonSignIn -> {
                if (validLogin() == null && validPassword() == null) {
                    viewModel.signIn(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
                }
            }
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val WHITESPACE = "^\\S+$"
    }
}
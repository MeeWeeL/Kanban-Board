package com.meeweel.kanban_board.ui.screens.registration

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
import com.meeweel.kanban_board.databinding.FragmentRegistrationBinding
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationState
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationViewModel
import java.util.regex.Pattern

class RegistrationFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() {
            return _binding!!
        }

    private val viewModel: AuthorizationViewModel by lazy {
        ViewModelProvider(this).get(AuthorizationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
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

    private fun renderIsSingIn(b: Boolean) {
        if (!b) {
            binding.loadingLayout.visibility = View.GONE
            Toast.makeText(requireContext(), "Authorization failed", Toast.LENGTH_SHORT).show()
        } else {
            MAIN.navController.navigate(R.id.action_authorizationFragment_to_mainScreenFragment)
        }
    }

    private fun initFocusListeners() {
        binding.editTextLogin.doOnTextChanged { _, _, _, _ ->
            binding.loginLayout.helperText = validLogin()
        }
        binding.editTextPassword.doOnTextChanged { _, _, _, _ ->
            binding.passwordLayout.helperText = validPassword()
        }
        binding.editTextConfirmPassword.doOnTextChanged { _, _, _, _ ->
            binding.confirmPasswordLayout.helperText = validConfirmPassword()
        }
    }

    private fun validConfirmPassword(): CharSequence? {
        val passwordConfirmText = binding.editTextConfirmPassword.editableText.toString()
        val passwordText = binding.editTextPassword.editableText.toString()
        if (passwordConfirmText.isEmpty()) {
            return "Required*"
        }
        if (passwordText != passwordConfirmText) {
            return "Пароли не совпадают"
        }
        return null
    }

    private fun validLogin(): CharSequence? {
        val loginText = binding.editTextLogin.editableText.toString()
        val p = Pattern.compile(AuthorizationFragment.WHITESPACE)
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
        val p = Pattern.compile(AuthorizationFragment.WHITESPACE)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSignUp -> {
                if (validLogin() == null && validPassword() == null && validConfirmPassword() == null) {
                    viewModel.signUp(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
                }
            }
            R.id.buttonSignIn -> {
                MAIN.navController.navigate(R.id.action_registrationFragment_to_textViewLoginRegistration)
            }
        }
    }

}
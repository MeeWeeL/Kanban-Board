package com.meeweel.kanban_board.ui.screens.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentRegistrationBinding
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.AUTHORIZATION_FAILED
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.ERROR
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.MIN_CHAR_LOGIN
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.MIN_CHAR_PASS
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.PASSWORDS
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.REQUIRED
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.SPACE
import com.meeweel.kanban_board.ui.screens.authorization.AuthorizationFragment.Companion.SYMBOLS
import com.meeweel.kanban_board.util.toast
import java.util.regex.Pattern
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding get() = _binding!!
    private val viewModel: AuthorizationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            Toast.makeText(requireContext(), AUTHORIZATION_FAILED, Toast.LENGTH_SHORT).show()
        } else
            MAIN.navController.navigate(R.id.action_authorizationFragment_to_mainScreenFragment)
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
            return REQUIRED
        }
        if (passwordText != passwordConfirmText) {
            return PASSWORDS
        }
        return null
    }

    private fun validLogin(): CharSequence? {
        val loginText = binding.editTextLogin.editableText.toString()
        val p = Pattern.compile(AuthorizationFragment.WHITESPACE_REGEX)
//        val k = Pattern.compile(SYMBOLS)
        return when {
            loginText.isEmpty() -> return REQUIRED
            !p.matcher(loginText).matches() -> SPACE
//            !k.matcher(loginText).matches() -> SYMBOLS
            loginText.length < 6 -> MIN_CHAR_LOGIN
            else -> null
        }
    }

    private fun validPassword(): CharSequence? {
        val passwordText = binding.editTextPassword.editableText.toString()
        val p = Pattern.compile(AuthorizationFragment.WHITESPACE_REGEX)
        return when {
            passwordText.isEmpty() -> REQUIRED
            !p.matcher(passwordText).matches() -> SPACE
            passwordText.length < 6 -> MIN_CHAR_PASS
            else -> null
        }
    }

    private fun renderAuth(state: AuthorizationState) {
        when (state) {
            is AuthorizationState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.editTextLogin.setText(state.data.login)
                binding.editTextPassword.setText(state.data.password)
            }
            is AuthorizationState.Loading -> binding.loadingLayout.visibility = View.VISIBLE
            is AuthorizationState.Done -> binding.loadingLayout.visibility = View.GONE
            else -> ERROR.toast(requireContext())
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSignUp -> if
                (validLogin() == null && validPassword() == null && validConfirmPassword() == null)
                    viewModel.signUp(
                        binding.editTextLogin.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
            R.id.buttonSignIn -> MAIN.navController
                .navigate(R.id.action_registrationFragment_to_textViewLoginRegistration)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
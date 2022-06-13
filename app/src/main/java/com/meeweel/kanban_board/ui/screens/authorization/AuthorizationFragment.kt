package com.meeweel.kanban_board.ui.screens.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentAuthorizationBinding
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.util.toast
import java.util.regex.Pattern
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthorizationFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding get() = _binding!!
    private val viewModel: AuthorizationViewModel by viewModel()

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
        val p = Pattern.compile(WHITESPACE_REGEX)
        val k = Pattern.compile(SYMBOLS_REGEX)
        return when {
            loginText.isEmpty() -> REQUIRED
            !p.matcher(loginText).matches() -> SPACE
            !k.matcher(loginText).matches() -> SYMBOLS
            loginText.length < 6 -> MIN_CHAR_LOGIN
            else -> null
        }

    }

    private fun validPassword(): CharSequence? {
        val passwordText = binding.editTextPassword.editableText.toString()
        val p = Pattern.compile(WHITESPACE_REGEX)
        return when {
            passwordText.isEmpty() -> REQUIRED
            !p.matcher(passwordText).matches() -> SPACE
            passwordText.length < 6 -> MIN_CHAR_PASS
            else -> null
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonSignUp ->
                MAIN.navController.navigate(R.id.action_textViewLoginRegistration_to_registrationFragment)
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
            is AuthorizationState.Loading -> binding.loadingLayout.visibility = View.VISIBLE
            is AuthorizationState.Done -> binding.loadingLayout.visibility = View.GONE
            else -> ERROR.toast(requireContext())
        }
    }

    private fun renderIsSingIn(b: Boolean) {
        if (!b) {
            binding.loadingLayout.visibility = View.GONE
            AUTHORIZATION_FAILED.toast(requireContext())
        } else MAIN.navController.navigate(R.id.action_authorizationFragment_to_mainScreenFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val WHITESPACE_REGEX = "[a-zA-Z0-9\\S]+"
        const val SYMBOLS_REGEX = "[a-zA-Z0-9.\\-_]+"
        const val REQUIRED = "Required*"
        const val SPACE = "Don't use space"
        const val SYMBOLS = "Don't use symbols"
        const val PASSWORDS = "Пароли не совпадают"
        const val MIN_CHAR_PASS = "Minimum 6 Character Password"
        const val MIN_CHAR_LOGIN = "Minimum 6 Character Login"
        const val ERROR = "Error"
        const val AUTHORIZATION_FAILED = "Authorization failed"
    }
}
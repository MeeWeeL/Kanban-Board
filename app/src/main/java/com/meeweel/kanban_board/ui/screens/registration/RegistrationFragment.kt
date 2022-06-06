package com.meeweel.kanban_board.ui.screens.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentRegistrationBinding
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.authorization.LoginValidator
import com.meeweel.kanban_board.ui.screens.authorization.PasswordValidator


class RegistrationFragment : Fragment(), View.OnClickListener {

    private val loginValidator = LoginValidator()
    private val passwordValidator = PasswordValidator()
    private val confirmPasswordValidator = ConfirmPasswordValidator()
    

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() {
            return _binding!!
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
        binding.buttonSignUpRegistration.setOnClickListener(this)
        binding.buttonSignInRegistration.setOnClickListener(this)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.buttonSignUpRegistration->{
                with(binding){
                    editTextLoginRegistration.addTextChangedListener(loginValidator)
                    editTextPasswordRegistration.addTextChangedListener(passwordValidator)
                    editTextConfirmPasswordRegistration.addTextChangedListener(confirmPasswordValidator)

//                    loginValidator.isValidLogin &&
                            if (passwordValidator.isValidPassword && confirmPasswordValidator.isConfirmPassword) {
                        Toast.makeText(
                            requireContext(),"Created a new user ", Toast.LENGTH_SHORT
                        ).show()
                    } else {
//                        val errorLogin = getString(R.string.error_login_validation)
                        val errorPassword = getString(R.string.error_password_validation)
                        val errorConfirmPassword = getString(R.string.error_confirm_password_validation)

//                        editTextLoginRegistration.error = errorLogin
                        editTextPasswordRegistration.error = errorPassword
                        editTextConfirmPasswordRegistration.error = errorConfirmPassword
                        Toast.makeText(requireContext(), "${confirmPasswordValidator.isConfirmPassword}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.buttonSignInRegistration->{
                MAIN.navController.navigate(R.id.action_registrationFragment_to_textViewLoginRegistration)
            }
        }
    }

}
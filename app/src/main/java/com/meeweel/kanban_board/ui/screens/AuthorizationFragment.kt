package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentAuthorizationBinding
import com.meeweel.kanban_board.ui.MAIN

class AuthorizationFragment : Fragment() {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding
        get() {
            return _binding!!
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
        binding.buttonSignIn.setOnClickListener {
            MAIN.navController.navigate(R.id.action_authorizationFragment_to_mainScreenFragment)
        }
        binding.buttonSignUp.setOnClickListener {
            MAIN.navController.navigate(R.id.action_textViewLoginRegistration_to_registrationFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
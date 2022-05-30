package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentRegistrationBinding
import com.meeweel.kanban_board.ui.MAIN


class RegistrationFragment : Fragment() {

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
        listenerButtonSignInRegistration()
    }

    private fun listenerButtonSignInRegistration() {
        binding.buttonSignInRegistration.setOnClickListener {
            MAIN.navController.navigate(R.id.action_registrationFragment_to_textViewLoginRegistration)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
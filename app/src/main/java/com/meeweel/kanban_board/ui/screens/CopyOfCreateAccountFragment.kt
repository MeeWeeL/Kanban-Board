package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentCopyOfCreateAccountBinding
import com.meeweel.kanban_board.ui.MAIN

class CopyOfCreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCopyOfCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCopyOfCreateAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onActionBarListener()
    }

    private fun onActionBarListener() {
        binding.leftTopAppBarCopyOfCreateAccount.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_copyOfCreateAccountFragment_to_mainScreenFragment)
        }
    }
}
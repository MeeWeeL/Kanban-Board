package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentBoardScreenBinding
import com.meeweel.kanban_board.ui.MAIN

class BoardScreenFragment : Fragment() {

    private var _binding: FragmentBoardScreenBinding? = null
    private val binding: FragmentBoardScreenBinding
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGoToLeft.setOnClickListener {
            MAIN.navController.navigate(R.id.action_boardScreenFragment_to_toDoFragment)
        }
        binding.buttonGoToRight.setOnClickListener {
            MAIN.navController.navigate(R.id.action_boardScreenFragment_to_doneFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentBoardScreenBinding
import com.meeweel.kanban_board.ui.MAIN

class BoardScreenFragment : Fragment(), View.OnTouchListener {
    private var _binding: FragmentBoardScreenBinding? = null
    private val binding: FragmentBoardScreenBinding
        get() {
            return _binding!!
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
        binding.flipperLeft.setOnTouchListener(this)
        binding.flipperRight.setOnTouchListener(this)
        onActionBarListener()
    }

    private fun onActionBarListener() {
        binding.leftTopAppBarBoardScreen.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_boardScreenFragment_to_mainScreenFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (view) {
            binding.flipperLeft -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        MAIN.navController.navigate(R.id.action_boardScreenFragment_to_toDoFragment)
                    }
                }
            }
            binding.flipperRight -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        MAIN.navController.navigate(R.id.action_boardScreenFragment_to_doneFragment)
                    }
                }
            }
        }
        return true
    }
}
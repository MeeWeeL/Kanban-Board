package com.meeweel.kanban_board.ui.fragments_from_board_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentBoardScreenBinding
import com.meeweel.kanban_board.databinding.FragmentDoneBinding
import com.meeweel.kanban_board.ui.MAIN

class DoneFragment : Fragment(), View.OnTouchListener {

    private var _binding: FragmentDoneBinding? = null
    private val binding: FragmentDoneBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.done.setOnTouchListener(this)
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when(view){
            binding.done->{
                when(motionEvent.action){
                    MotionEvent.ACTION_MOVE->{
                        MAIN.navController.navigate(R.id.action_doneFragment_to_boardScreenFragment)
                    }
                }
            }
        }
        return true
    }

}
package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentMainScreenBinding
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : Fragment() {

    lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainScreenFragment.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_boardScreenFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_screen_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //TODO
        }
        return super.onOptionsItemSelected(item)
    }
}
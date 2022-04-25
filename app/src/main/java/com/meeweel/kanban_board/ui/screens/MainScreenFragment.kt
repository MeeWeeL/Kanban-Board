package com.meeweel.kanban_board.ui.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentMainScreenBinding
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
    get(){
        return _binding!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabMainScreen.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_boardScreenFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_screen_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            androidx.appcompat.R.id.home->{
                MAIN.navController.navigate(R.id.action_mainScreenFragment_to_copyOfCreateAccountFragment)
            }
//            R.id.action_add_board_menu ->{
//               MAIN.navController.navigate(R.id.action_mainScreenFragment_to_copyOfCreateAccountFragment)
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.meeweel.kanban_board.ui.screens.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentMainScreenBinding
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : BaseMainScreenFragment() {

    private var _binding: FragmentMainScreenBinding? = null
    override val binding: FragmentMainScreenBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFabListener()
        onActionBarListener()
        workWhitItemMenuInToolbar()
    }

    private fun workWhitItemMenuInToolbar() {
        binding.leftTopAppBarMainScreenFragment.inflateMenu(R.menu.menu_main_screen_add)
        binding.leftTopAppBarMainScreenFragment.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    Toast.makeText(context, "Юххуу", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun onActionBarListener() {
        binding.leftTopAppBarMainScreenFragment.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_copyOfCreateAccountFragment)
        }
    }

    private fun onFabListener() {
        binding.fabMainScreen.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_boardScreenFragment)
        }
    }
}
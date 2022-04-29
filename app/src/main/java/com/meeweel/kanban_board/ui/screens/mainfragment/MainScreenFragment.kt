package com.meeweel.kanban_board.ui.screens.mainfragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : BaseMainScreenFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFabListener()
        onActionBarListener()
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

    private fun actionBar() {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_screen_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                Toast.makeText(context, "You push button +", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
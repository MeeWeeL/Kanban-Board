package com.meeweel.kanban_board.ui

import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(ThemeHolder.theme)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        MAIN = this
    }

    fun changeTheme(@StyleRes th: Int) {
        ThemeHolder.theme = th
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object ThemeHolder {
        var theme = R.style.Theme_KanbanBoard
    }
}
package com.meeweel.kanban_board.ui

import android.graphics.Color
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
        setColors()
    }

    private fun setColors() {
        white = resources.getColor(R.color.white)
        green = resources.getColor(R.color.priorityGreen)
        yellow = resources.getColor(R.color.priorityYellow)
        orange = resources.getColor(R.color.priorityOrange)
        red = resources.getColor(R.color.priorityRed)
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

        var white: Int = 0
        var green: Int = 0
        var yellow: Int = 0
        var orange: Int = 0
        var red: Int = 0
    }
}
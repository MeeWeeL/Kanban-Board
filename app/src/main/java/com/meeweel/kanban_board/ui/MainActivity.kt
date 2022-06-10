package com.meeweel.kanban_board.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() {
            return _binding!!
        }
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        recreate()

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        MAIN = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
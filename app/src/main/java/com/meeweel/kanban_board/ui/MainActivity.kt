package com.meeweel.kanban_board.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.meeweel.kanban_board.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.apply {
                beginTransaction().replace(
                    R.id.container, AuthorizationFragment.newInstance()
                )
            }
        }
    }
}
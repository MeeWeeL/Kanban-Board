package com.meeweel.kanban_board.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.meeweel.kanban_board.databinding.ActivityViewPagerBinding
import com.meeweel.kanban_board.ui.screens.boardscreen.inprogress.ViewPagerAdapter

class ViewPagerActivity : AppCompatActivity() {
    private var _binding: ActivityViewPagerBinding? = null
    private val binding: ActivityViewPagerBinding
        get() {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        with(binding){
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when(position) {
                    0 -> "Первый"
                    1 -> "Второй"
                    2 -> "Крайний"
                    else -> throw IllegalStateException()
                }
            }.attach()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
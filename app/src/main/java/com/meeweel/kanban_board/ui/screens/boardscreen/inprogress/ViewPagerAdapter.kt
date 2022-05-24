package com.meeweel.kanban_board.ui.screens.boardscreen.inprogress

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragments: Int, fragmentManager: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val _fragments: Int = fragments

    override fun getItemCount(): Int = _fragments

    override fun createFragment(position: Int): Fragment = InProgressFragment()
//        arguments = bundleOf(
//            "todoFragment" to _fragments[position],
//            "inProgressFragment" to _fragments[position],
//            "doneFragment" to _fragments[position]
//        )

}

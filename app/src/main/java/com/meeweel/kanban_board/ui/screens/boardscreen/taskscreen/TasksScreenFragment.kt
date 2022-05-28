package com.meeweel.kanban_board.ui.screens.boardscreen.taskscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentTasksScreenBinding
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.boardscreen.BoardScreenFragmentViewModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.done.DoneFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.inprogress.InProgressFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.todo.ToDoFragment

class TasksScreenFragment : Fragment() {

    private lateinit var binding: FragmentTasksScreenBinding

    private val viewModel: BoardScreenFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(BoardScreenFragmentViewModel::class.java) //
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                initPositionListener(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        onActionBarListener()
        workWhitItemMenuInToolbar()
    }

    private fun initPositionListener(position: Int) {
        dropPositions()
        when (position) {
            0 -> {
                binding.position0.setImageResource(R.drawable.swipe_indicator_active)
                binding.leftTopAppBarBoardScreen.title = TODO
            }
            1 -> {
                binding.position1.setImageResource(R.drawable.swipe_indicator_active)
                binding.leftTopAppBarBoardScreen.title = IN_PROGRESS
            }
            else -> {
                binding.position2.setImageResource(R.drawable.swipe_indicator_active)
                binding.leftTopAppBarBoardScreen.title = DONE
            }
        }
    }

    private fun dropPositions() {
        binding.position0.setImageResource(R.drawable.swipe_indicator_passive)
        binding.position1.setImageResource(R.drawable.swipe_indicator_passive)
        binding.position2.setImageResource(R.drawable.swipe_indicator_passive)
    }

    private fun onActionBarListener() {
        binding.leftTopAppBarBoardScreen.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_taskScreenFragment_to_mainScreenFragment)
        }
    }

    private fun workWhitItemMenuInToolbar() {
        binding.leftTopAppBarBoardScreen.inflateMenu(R.menu.menu_main_screen_add)
        binding.leftTopAppBarBoardScreen.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    Toast.makeText(requireContext(), PUSH_BUTTON, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    inner class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 3

        override fun createFragment(position: Int): Fragment {
            val fragment = when (position) {
                0 -> ToDoFragment(viewModel)
                1 -> InProgressFragment(viewModel)
                else -> DoneFragment(viewModel)
            }
            return fragment
        }
    }

    companion object {
        const val TODO = "ToDo"
        const val IN_PROGRESS = "In progress"
        const val DONE = "Done"
        const val PUSH_BUTTON = "You push button"
    }
}
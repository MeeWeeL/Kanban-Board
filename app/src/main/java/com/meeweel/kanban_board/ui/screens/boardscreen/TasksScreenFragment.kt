package com.meeweel.kanban_board.ui.screens.boardscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.BottomSheetCreateNewTaskBinding
import com.meeweel.kanban_board.databinding.FragmentTasksScreenBinding
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.DoneFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.InProgressFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.ToDoFragment
import com.meeweel.kanban_board.ui.screens.mainfragment.MainScreenFragmentRecyclerAdapter.Companion.ARG_BOARD_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksScreenFragment : Fragment() {

    private var isClicked = false
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val tpBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }

    private var _binding: FragmentTasksScreenBinding? = null
    private val binding: FragmentTasksScreenBinding get() = _binding!!
    private val viewModel: TasksScreenFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.boardId = requireArguments().getInt(ARG_BOARD_ID)
        viewModel.synchronizeData()
        initPager()
        onActionBarListener()
        workWhitItemMenuInToolbar()
        onFabListener()
    }

    private fun initPager() {
        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                initPositionListener(position)
            }
        })
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
                    showCreatingSheet()
                    true
                }
                else -> false
            }
        }
    }

    private fun showCreatingSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetCreateNewTaskBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.taskBtn.setOnClickListener {
            viewModel.createTask(
                TaskModel(
                    0,
                    bottomSheetBinding.taskTitle.text.toString(),
                    bottomSheetBinding.taskDescription.text.toString()
                )
            )
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
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

    private fun onFabListener() {
        binding.fabMainScreen.setOnClickListener {
            mainFabClicked()
        }
        binding.fabAdd.setOnClickListener {
            showCreatingSheet()
        }
        binding.fabBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_taskScreenFragment_to_mainScreenFragment)
        }
    }

    private fun mainFabClicked() {
        setVisibility(isClicked)
        setAnimation(isClicked)
        setClickable(isClicked)
        isClicked = !isClicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.fabBack.visibility = View.VISIBLE
            binding.fabAdd.visibility = View.VISIBLE
        } else {
            binding.fabBack.visibility = View.GONE
            binding.fabAdd.visibility = View.GONE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.fabBack.startAnimation(fromBottom)
            binding.fabAdd.startAnimation(fromBottom)
            binding.fabMainScreen.startAnimation(rotateOpen)
        } else {
            binding.fabBack.startAnimation(tpBottom)
            binding.fabAdd.startAnimation(tpBottom)
            binding.fabMainScreen.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        binding.fabBack.isClickable = !clicked
        binding.fabAdd.isClickable = !clicked
    }

    companion object {
        const val TODO = "ToDo"
        const val IN_PROGRESS = "In progress"
        const val DONE = "Done"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
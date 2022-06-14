package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.BottomSheetEditTaskBinding
import com.meeweel.kanban_board.databinding.BottomSheetTaskBinding
import com.meeweel.kanban_board.databinding.FragmentListOfTasksBinding
import com.meeweel.core.basemodels.Priority
import com.meeweel.core.basemodels.Status
import com.meeweel.core.basemodels.TaskModel
import com.meeweel.core.basemodels.states.BoardState
import com.meeweel.kanban_board.ui.screens.boardscreen.TasksScreenFragmentViewModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.adapter.BaseBoardScreenAdapter
import com.meeweel.kanban_board.util.setBrands

abstract class BaseTaskListFragment(internal val viewModel: TasksScreenFragmentViewModel) : Fragment() {

    internal var taskPopupListener: PopupMenu.OnMenuItemClickListener? = null
    private var _binding: FragmentListOfTasksBinding? = null
    internal open val binding: FragmentListOfTasksBinding get() = _binding!!
    internal val adapter = BaseBoardScreenAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        workLivedata()
        burgerClick()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.synchronizeData()
        }
    }

    private fun setTaskPopupListener(task: TaskModel) {
        taskPopupListener = PopupMenu.OnMenuItemClickListener {
            when (it.itemId) {
                R.id.to_done -> viewModel.updateTask(task.also { task -> task.status = Status.DONE })
                R.id.to_in_progress -> viewModel.updateTask(task.also { task -> task.status = Status.IN_PROGRESS })
                R.id.to_todo -> viewModel.updateTask(task.also { task -> task.status = Status.TO_DO })
                R.id.edit -> showEditBottomSheet(task)
                R.id.delete -> viewModel.removeTask(task.id)
            }
            true
        }
    }

    private fun burgerClick() {
        adapter.setBurgerClickListener(object : OnBurgerClickListener {
            override fun onBurgerClick(view: View, task: TaskModel) {
                setTaskPopupListener(task)
                val popupMenu = PopupMenu(
                    requireContext(),
                    view,
                    Gravity.CENTER
                )
                popupMenu.inflate(popupMenu())
                popupMenu.setForceShowIcon(true)
                popupMenu.setOnMenuItemClickListener(taskPopupListener)
                popupMenu.show()
            }
        })
    }

    abstract fun popupMenu() : Int

    internal fun showBottomSheet(task: TaskModel) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetTaskBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.apply {
            taskTitle.text = task.name
            taskDescription.text = task.description
            taskPriority.text = task.priority.text
            taskPerformer.text = task.performer
        }
        bottomSheetDialog.show()
    }

    internal fun showEditBottomSheet(task: TaskModel) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetEditTaskBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        val brands = Priority.values()
        bottomSheetBinding.apply {
            taskTitle.text = SpannableStringBuilder(task.name)
            taskDescription.text = SpannableStringBuilder(task.description)
            taskPriority.apply {
                setBrands(requireContext(), brands.map { it.text })
                setSelection(task.priority.int)
            }
            taskPerformer.text = SpannableStringBuilder(task.performer)
            taskBtn.setOnClickListener {
                viewModel.updateTask(
                    TaskModel(
                        task.id,
                        taskTitle.text.toString(),
                        taskDescription.text.toString(),
                        task.status,
                        brands[taskPriority.selectedItemPosition],
                        taskPerformer.text.toString()
                    )
                )
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.show()
    }

    abstract fun workLivedata()

    internal fun renderData(data: BoardState) = when (data) {
        is BoardState.Success -> {
            binding.refreshLayout.isRefreshing = false
            val dataList = data.data
            binding.loadingLayoutBoardScreen.visibility = View.GONE
            setAdapterData(dataList)
        }
        is BoardState.Loading -> {
            binding.loadingLayoutBoardScreen.visibility = View.VISIBLE
        }
        is BoardState.Error -> {
            binding.refreshLayout.isRefreshing = false
            binding.loadingLayoutBoardScreen.visibility = View.GONE
        }
    }

    private fun setAdapter() {
        binding.boardScreenFragmentRecyclerView.adapter = adapter
    }

    private fun setAdapterData(dataList: List<TaskModel>) = adapter.setData(dataList)

    interface OnTaskClickListener {
        fun showTaskSheet(task: TaskModel)
    }

    interface OnLongTaskClickListener {
        fun showTaskEditSheet(task: TaskModel)
    }

    interface OnBurgerClickListener {
        fun onBurgerClick(view: View, task: TaskModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeClickListeners()
    }
}
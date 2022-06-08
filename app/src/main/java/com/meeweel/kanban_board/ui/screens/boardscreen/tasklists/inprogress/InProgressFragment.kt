package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.inprogress

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.BottomSheetEditTaskBinding
import com.meeweel.kanban_board.databinding.BottomSheetTaskBinding
import com.meeweel.kanban_board.databinding.FragmentListOfTasksBinding
import com.meeweel.kanban_board.domain.basemodels.Priority
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.BoardState
import com.meeweel.kanban_board.ui.screens.boardscreen.BoardScreenFragmentViewModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.BaseBoardScreenFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.OnBurgerClickListener
import com.meeweel.kanban_board.util.setBrands

class InProgressFragment(viewModel: BoardScreenFragmentViewModel) :
    BaseBoardScreenFragment(viewModel) {

    override val binding: FragmentListOfTasksBinding
        get() {
            return _binding!!
        }

    private val adapter =
        InProgressRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        burgerClick()
        adapter.setItemListener(object : OnTaskClickListener {
            override fun showTaskSheet(task: TaskModel) {
                showBottomSheet(task)
            }
        })
        adapter.setLongItemListener(object : OnLongTaskClickListener {
            override fun showTaskEditSheet(task: TaskModel) {
                showEditBottomSheet(task)
            }
        })
    }

    private fun showBottomSheet(task: TaskModel) {
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

    private fun showEditBottomSheet(task: TaskModel) {
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

    override fun workLivedata() {
        val observer =
            Observer<BoardState> { a ->
                renderData(a)
            }
        viewModel.getInProgressData().observe(
            viewLifecycleOwner,
            observer
        )
    }

    private fun burgerClick() {
        adapter.setBurgerClickListener(object : OnBurgerClickListener {
            override fun onBurgerClick(view: AppCompatImageButton) {
                val popupMenu = PopupMenu(
                    requireContext(),
                    view,
                    Gravity.CENTER
                )
                popupMenu.inflate(R.menu.popup_menu)
                popupMenu.setForceShowIcon(true)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.moveTo -> {
                            Toast.makeText(requireContext(), "moveTo", Toast.LENGTH_SHORT).show()
                        }
                        R.id.changePriority -> {
                            Toast.makeText(requireContext(), "changePriority", Toast.LENGTH_SHORT)
                                .show()
                        }
                        R.id.delete -> {
                            Toast.makeText(requireContext(), "delete", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        })
    }

    override fun setAdapter() {
        binding.boardScreenFragmentRecyclerView.adapter = adapter
    }

    override fun setAdapterData(dataList: List<TaskModel>) {
        val list = mutableListOf<TaskModel>()
        for (item in dataList) if (item.status == Status.IN_PROGRESS) list.add(item)
        adapter.setData(list)
    }

    interface OnTaskClickListener {
        fun showTaskSheet(task: TaskModel)
    }

    interface OnLongTaskClickListener {
        fun showTaskEditSheet(task: TaskModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeClickListeners()
    }
}
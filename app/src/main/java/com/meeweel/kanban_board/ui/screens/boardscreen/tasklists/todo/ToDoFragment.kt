package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.todo

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentListOfTasksBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.BoardState
import com.meeweel.kanban_board.domain.basemodels.states.TasksAppState
import com.meeweel.kanban_board.ui.screens.boardscreen.BoardScreenFragmentViewModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.BaseBoardScreenFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.OnBurgerClickListener

class ToDoFragment(viewModel: BoardScreenFragmentViewModel) : BaseBoardScreenFragment(viewModel) {

    override val binding: FragmentListOfTasksBinding
        get() {
            return _binding!!
        }

    private val adapter = ToDoRecyclerAdapter() // Адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabToDo()
        burgerClick()
    }

    override fun workLivedata() {
        val observer =
            Observer<BoardState> { a -> // Создаём подписчика и говорим ему что делать если данные обновились
                renderData(a)
            }
        viewModel.getToDoData().observe(
            viewLifecycleOwner,
            observer
        )
    }

    private fun burgerClick() {
        adapter.setBurgerClickListener(object : OnBurgerClickListener{
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
        binding.boardScreenFragmentRecyclerView.adapter =
            adapter // Приаттачиваем наш адаптер к ресайклеру, чтобы ресайклер знал, что делать
    }

    override fun setAdapterData(dataList: List<TaskModel>) {
        val list = mutableListOf<TaskModel>()
        for (item in dataList) if (item.status == Status.TO_DO) list.add(item)
        adapter.setData(list)
    }

    private fun fabToDo() {
//        binding.fabToDo.setOnClickListener {
//            Toast.makeText(context, "FabToDo", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeClickListeners()
    }
}
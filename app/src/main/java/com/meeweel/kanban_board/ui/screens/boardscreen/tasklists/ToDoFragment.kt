package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.R
import com.meeweel.core.basemodels.TaskModel
import com.meeweel.core.basemodels.states.BoardState
import com.meeweel.kanban_board.ui.screens.boardscreen.TasksScreenFragmentViewModel

class ToDoFragment(viewModel: TasksScreenFragmentViewModel) : BaseTaskListFragment(viewModel) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setItemListener(object : OnTaskClickListener {
            override fun showTaskSheet(task: TaskModel) { showBottomSheet(task) }
        })
        adapter.setLongItemListener(object : OnLongTaskClickListener {
            override fun showTaskEditSheet(task: TaskModel) { showEditBottomSheet(task) }
        })
    }

    override fun workLivedata() {
        val observer = Observer<BoardState> { a -> renderData(a) }
        viewModel.getToDoData().observe(viewLifecycleOwner, observer)
    }

    override fun popupMenu(): Int {
        return R.menu.popup_menu_todo
    }
}
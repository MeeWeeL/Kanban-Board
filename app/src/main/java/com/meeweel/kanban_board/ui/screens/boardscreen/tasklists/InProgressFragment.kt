package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.BoardState
import com.meeweel.kanban_board.ui.screens.boardscreen.TasksScreenFragmentViewModel

class InProgressFragment(viewModel: TasksScreenFragmentViewModel) :
    BaseTaskListFragment(viewModel) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun popupMenu(): Int {
        return R.menu.popup_menu_in_progress
    }

    override fun setAdapterData(dataList: List<TaskModel>) {
        val list = mutableListOf<TaskModel>()
        for (item in dataList) if (item.status == Status.IN_PROGRESS) list.add(item)
        adapter.setData(list)
    }
}
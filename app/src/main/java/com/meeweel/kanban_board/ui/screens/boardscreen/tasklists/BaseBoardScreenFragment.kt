package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.meeweel.kanban_board.databinding.FragmentListOfTasksBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.BoardState
import com.meeweel.kanban_board.domain.basemodels.states.TasksAppState
import com.meeweel.kanban_board.ui.screens.boardscreen.BoardScreenFragmentViewModel

abstract class BaseBoardScreenFragment(internal val viewModel: BoardScreenFragmentViewModel) : Fragment() {

    internal var _binding: FragmentListOfTasksBinding? = null
    internal open val binding: FragmentListOfTasksBinding
        get() {
            return _binding!!
        }

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
    }

    abstract fun workLivedata()

    abstract fun setAdapter()

    internal fun renderData(data: BoardState) = when (data) {
        is BoardState.Success -> {
            val dataList = data.data
            binding.loadingLayoutBoardScreen.visibility = View.GONE
            setAdapterData(dataList)
        }
        is BoardState.Loading -> {
            binding.loadingLayoutBoardScreen.visibility = View.VISIBLE
        }
        is BoardState.Error -> {
            binding.loadingLayoutBoardScreen.visibility = View.GONE

        }
    }

    abstract fun setAdapterData(dataList: List<TaskModel>)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        var board: BoardModel? = null
    }
}
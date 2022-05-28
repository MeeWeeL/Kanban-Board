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
import com.meeweel.kanban_board.domain.basemodels.states.TasksAppState
import com.meeweel.kanban_board.ui.screens.boardscreen.BoardScreenFragmentViewModel

abstract class BaseBoardScreenFragment(private val viewModel: BoardScreenFragmentViewModel) : Fragment() {

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
        workLivedata()
    }

    private fun workLivedata() {
        setAdapter()

        val observer =
            Observer<TasksAppState> { a -> // Создаём подписчика и говорим ему что делать если данные обновились
                renderData(a)
            }
        viewModel.getData().observe(
            viewLifecycleOwner,
            observer
        ) // Подписываем нашего подписчика на лайвдату из вьюмодели
    }

    abstract fun setAdapter()

    private fun renderData(data: TasksAppState) = when (data) {
        is TasksAppState.Success -> {
            val dataList = data.data
            binding.loadingLayoutBoardScreen.visibility = View.GONE
            setAdapterData(dataList)
        }
        is TasksAppState.Loading -> {
            binding.loadingLayoutBoardScreen.visibility = View.VISIBLE
        }
        is TasksAppState.Error -> {
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
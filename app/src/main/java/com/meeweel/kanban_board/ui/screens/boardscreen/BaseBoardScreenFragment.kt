package com.meeweel.kanban_board.ui.screens.boardscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentInProgressBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.TasksAppState
import com.meeweel.kanban_board.ui.FragmentReplacer
import com.meeweel.kanban_board.ui.MAIN
import kotlin.random.Random

abstract class BaseBoardScreenFragment(private val layoutId: Int) : Fragment() {

    val pageId = Random.nextLong(2021, 2021*3)
    var pagePos = -1
    protected lateinit var fragmentReplacer: FragmentReplacer


    internal var _binding: FragmentInProgressBinding? = null
    internal open val binding: FragmentInProgressBinding
        get() {
            return _binding!!
        }

    private val viewModel: BoardScreenFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(BoardScreenFragmentViewModel::class.java) //
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workLivedata()
    }

    fun setPageInfo(pagePos: Int, fragmentReplacer: FragmentReplacer) {
        this.pagePos = pagePos
        this.fragmentReplacer = fragmentReplacer
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

    internal fun onActionBarListener() {
        binding.leftTopAppBarBoardScreen.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_boardScreenFragment_to_mainScreenFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        var board: BoardModel? = null
    }
}
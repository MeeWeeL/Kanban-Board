package com.meeweel.kanban_board.ui.screens.boardscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentInProgressBinding
import com.meeweel.kanban_board.databinding.FragmentViewpagerBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.TasksAppState
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.boardscreen.inprogress.ViewPagerAdapter

abstract class BaseBoardScreenFragment : Fragment() {

    private val countBoards = 3

    private lateinit var adapterViewPager: ViewPager2
     private val bindingViewPager: FragmentViewpagerBinding? = null

    internal var _binding: FragmentInProgressBinding? = null
    internal open val binding: FragmentInProgressBinding
        get() {
            return _binding!!
        }

    private val viewModel: BoardScreenFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(BoardScreenFragmentViewModel::class.java) //
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pagerAdapter = ViewPagerAdapter(
            countBoards,
            childFragmentManager,
            lifecycle
        )
        bindingViewPager?.viewPager?.adapter = pagerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(layoutInflater, container, false)
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
        var POSITION_ARG = "position_arg"
    }
}
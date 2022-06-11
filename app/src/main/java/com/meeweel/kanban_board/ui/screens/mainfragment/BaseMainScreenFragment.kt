package com.meeweel.kanban_board.ui.screens.mainfragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.*
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.Priority
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.BoardsAppState
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.BaseTaskListFragment
import com.meeweel.kanban_board.util.setBrands

abstract class BaseMainScreenFragment : Fragment() {

    private var taskPopupListener: PopupMenu.OnMenuItemClickListener? = null
    private var _binding: FragmentMainScreenBinding? = null
    internal open val binding: FragmentMainScreenBinding
        get() {
            return _binding!!
        }

    internal val viewModel: MainFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(MainFragmentViewModel::class.java) //
    }
    private val adapter = MainScreenFragmentRecyclerAdapter() // Адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workLivedata()
        burgerClick()
    }

    private fun burgerClick() {
        adapter.setBurgerClickListener(object : OnBurgerClickListener {
            override fun onBurgerClick(view: View, board: BoardModel) {
                setBoardPopupListener(board)
                val popupMenu = PopupMenu(
                    requireContext(),
                    view,
                    Gravity.CENTER
                )
                popupMenu.inflate(R.menu.popup_menu_board)
                popupMenu.setForceShowIcon(true)
                popupMenu.setOnMenuItemClickListener(taskPopupListener)
                popupMenu.show()
            }
        })
    }

    private fun setBoardPopupListener(board: BoardModel) {
        taskPopupListener = PopupMenu.OnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit -> {
                    showEditBottomSheet(board)
                }
                R.id.delete -> {
                    viewModel.removeBoard(board.id)
                }
                R.id.details -> {
                    showBottomSheet(board)
                }
            }
            true
        }
    }

    private fun workLivedata() {
        binding.mainScreenFragmentRecyclerView.adapter =
            adapter // Приаттачиваем наш адаптер к ресайклеру, чтобы ресайклер знал, что делать

        val observer =
            Observer<BoardsAppState> { a -> // Создаём подписчика и говорим ему что делать если данные обновились
                renderData(a)
            }
        viewModel.getData().observe(
            viewLifecycleOwner,
            observer
        ) // Подписываем нашего подписчика на лайвдату из вьюмодели
        viewModel.getBoards() // Просим вьюмодель обновить свои данные (На всякий случай)
    }

    private fun renderData(data: BoardsAppState) = when (data) {
        is BoardsAppState.Success -> {
            val dataList = data.data
            binding.loadingLayoutMainScreen.visibility = View.GONE
            adapter.setData(dataList)
        }
        is BoardsAppState.Loading -> {
            binding.loadingLayoutMainScreen.visibility = View.VISIBLE
        }
        is BoardsAppState.Error -> {
            binding.loadingLayoutMainScreen.visibility = View.GONE

        }
    }

    internal fun showBottomSheet(board: BoardModel) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetBoardBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        var toDoCounter = 0
        var inProgressCounter = 0
        var doneCounter = 0
        for (item in board.toDoList) {
            when (item.status) {
                Status.TO_DO -> toDoCounter++
                Status.IN_PROGRESS -> inProgressCounter++
                else -> doneCounter++
            }
        }
        bottomSheetBinding.apply {
            boardTitle.text = board.name
            boardHost.text = board.host
            boardTodoCounter.text = toDoCounter.toString()
            boardInProgressCounter.text = inProgressCounter.toString()
            boardDoneCounter.text = doneCounter.toString()
        }
        bottomSheetDialog.show()
    }

    private fun showEditBottomSheet(board: BoardModel) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = BottomSheetEditBoardBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.apply {
            boardTitle.text = SpannableStringBuilder(board.name)
            boardBtn.setOnClickListener {
                viewModel.updateBoard(board.also { board -> board.name = boardTitle.text.toString() })
                bottomSheetDialog.dismiss()
            }
        }
        bottomSheetDialog.show()
    }

    interface OnBurgerClickListener {
        fun onBurgerClick(view: View, board: BoardModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.inprogress

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentListOfTasksBinding
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.screens.boardscreen.BoardScreenFragmentViewModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.BaseBoardScreenFragment

class InProgressFragment(viewModel: BoardScreenFragmentViewModel) : BaseBoardScreenFragment(viewModel) {

    override val binding: FragmentListOfTasksBinding
        get() {
            return _binding!!
        }

    private val adapter =
        InProgressRecyclerAdapter()  // Адаптеры для фрагментов отличаются, поэтому они создаются тут, а не в базовом фрагменте

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfTasksBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.setBurgerClickListener(object : OnBurgerClickListener {
            override fun onBurgerClick() {
                val popupMenu = PopupMenu(requireContext(), view, Gravity.CENTER)
                popupMenu.inflate(R.menu.popup_menu)
                popupMenu.setForceShowIcon(true)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.moveTo -> {

                        }
                        R.id.changePriority -> {

                        }
                        R.id.delete -> {

                        }
                    }
                    true
                }
                popupMenu.show()
            }
        })
    }

    override fun setAdapter() { // В базовом фрагменте ещё нет адаптера, поэтому приаттачиваю его здесь
        binding.boardScreenFragmentRecyclerView.adapter =
            adapter
    }

    override fun setAdapterData(dataList: List<TaskModel>) { // Тут получаем список тасок всех
        val list = mutableListOf<TaskModel>() // Создаём список для нужных в этом фрагменте тасок
        for (item in dataList) if (item.status == Status.IN_PROGRESS) list.add(item) // Тут уже добавляем подходящие (С нужным статусом)
        adapter.setData(list) // Отправляем в адаптер
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeClickListeners() // Зануляю листенеры на бургерах, которые в адаптере навешивал
    }

    interface OnBurgerClickListener {
        fun onBurgerClick()
    }
}
package com.meeweel.kanban_board.ui.screens.boardscreen.inprogress

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentBoardScreenBinding
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.boardscreen.BaseBoardScreenFragment

class InProgressFragment : BaseBoardScreenFragment(), View.OnTouchListener {

    override val binding: FragmentBoardScreenBinding
        get() {
            return _binding!!
        }

    private val adapter = InProgressRecyclerAdapter()  // Адаптеры для фрагментов отличаются, поэтому они создаются тут, а не в базовом фрагменте

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipe.setOnTouchListener(this)
        binding.swipe.setOnTouchListener(this)
        onActionBarListener()
        workWhitItemMenuInToolbar()

        adapter.setBurgerClickListener(object : OnBurgerClickListener {
            override fun onBurgerClick() {
                TODO("Вот тут нужно указать, что делать при нажатии на бургер")
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

    private fun workWhitItemMenuInToolbar() {
        binding.leftTopAppBarBoardScreen.inflateMenu(R.menu.menu_main_screen_add)
        binding.leftTopAppBarBoardScreen.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add -> {
                    Toast.makeText(requireContext(), "You push button", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeClickListeners() // Зануляю листенеры на бургерах, которые в адаптере навешивал
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (view) {
            binding.swipe -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        MAIN.navController.navigate(R.id.action_boardScreenFragment_to_toDoFragment)
                    }
                }
            }
            binding.swipe -> {
                when (motionEvent.action) {
                    MotionEvent.ACTION_MOVE -> {
                        MAIN.navController.navigate(R.id.action_boardScreenFragment_to_doneFragment)
                    }
                }
            }
        }
        return true
    }

    interface OnBurgerClickListener {
        fun onBurgerClick()
    }
}
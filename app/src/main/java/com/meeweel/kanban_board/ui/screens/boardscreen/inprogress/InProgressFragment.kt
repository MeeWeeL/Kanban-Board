package com.meeweel.kanban_board.ui.screens.boardscreen.inprogress

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentInProgressBinding
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.screens.boardscreen.BaseBoardScreenFragment

class InProgressFragment : BaseBoardScreenFragment() {

    override val binding: FragmentInProgressBinding
        get() {
            return _binding!!
        }

    private val adapter =
        InProgressRecyclerAdapter()  // Адаптеры для фрагментов отличаются, поэтому они создаются тут, а не в базовом фрагменте

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        with(binding) {
//            viewPager.adapter = object : FragmentStateAdapter(this@InProgressFragment){
//                override fun getItemCount(): Int {
//                    return 3
//                }
//
//                override fun createFragment(position: Int): Fragment {
//                    TODO("Not yet implemented")
//                }
//
//            }
//            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    Toast.makeText(requireContext(), "Hi", Toast.LENGTH_SHORT).show()
//                }
//            })
//            viewPager.setPageTransformer { page, position ->
//                val viewPager = page.parent.parent as ViewPager2
//                val offset = position * -(2 * 120)
//                if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
//                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
//                        page.translationX = -offset
//                    } else {
//                        page.translationX = offset
//                    }
//                } else {
//                    page.translationY = offset
//                }
//            }
//
//            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                tab.text = "1"
//            }.attach()
//        }

        onActionBarListener()
        workWhitItemMenuInToolbar()

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

    interface OnBurgerClickListener {
        fun onBurgerClick()
    }
}
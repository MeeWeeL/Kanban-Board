package com.meeweel.kanban_board.ui.screens.mainfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentMainScreenBinding
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
        get() {
            return _binding!!
        }

    private val viewModel: MainFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(MainFragmentViewModel::class.java) //
    }
    private val adapter = MainFragmentRecyclerAdapter() // Адаптер

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFabListener()
        binding.leftTopAppBarMainScreenFragment.setNavigationOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_copyOfCreateAccountFragment)
        }

        binding.mainScreenFragmentRecyclerView.adapter =
            adapter // Приаттачиваем наш адаптер к ресайклеру, чтобы ресайклер знал, что делать

        val observer = Observer<AppState> { a -> // Создаём подписчика и говорим ему что делать если данные обновились
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer) // Подписываем нашего подписчика на лайвдату из вьюмодели
        viewModel.getLocalData() // Просим вьюмодель обновить свои данные (На всякий случай)
    }

    private fun renderData(data: AppState) = when (data) {
        is AppState.Success -> {
            val dataList = data.data
//            binding.loadingLayout.visibility = View.GONE //
            adapter.setData(dataList)
        }
        is AppState.Loading -> {
//            binding.loadingLayout.visibility = View.VISIBLE //
        }
        is AppState.Error -> {
//            binding.loadingLayout.visibility = View.GONE //

        }
    }

    private fun onFabListener() {
        binding.fabMainScreen.setOnClickListener {
            MAIN.navController.navigate(R.id.action_mainScreenFragment_to_boardScreenFragment)
        }
    }

    private fun actionBar() {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_screen_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
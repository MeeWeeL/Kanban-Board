package com.meeweel.kanban_board.ui.screens.todoscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.kanban_board.databinding.FragmentToDoBinding
import com.meeweel.kanban_board.ui.screens.AppState

abstract class BaseToDoScreenFragment : Fragment(),
    ToDoScreenFragmentRecyclerAdapter.OnItemClickListener {

    private var _binding: FragmentToDoBinding? = null
    internal open val binding: FragmentToDoBinding
        get() {
            return _binding!!
        }

    private val viewModel: ToDoFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(ToDoFragmentViewModel::class.java) //
    }
    private val adapter = ToDoScreenFragmentRecyclerAdapter(this) // Адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workLivedata()
    }

    private fun workLivedata() {
        binding.toDoScreenFragmentRecyclerView.adapter =
            adapter // Приаттачиваем наш адаптер к ресайклеру, чтобы ресайклер знал, что делать

        val observer =
            Observer<AppState> { a -> // Создаём подписчика и говорим ему что делать если данные обновились
                renderData(a)
            }
        viewModel.getData().observe(
            viewLifecycleOwner,
            observer
        ) // Подписываем нашего подписчика на лайвдату из вьюмодели
        viewModel.getBoards() // Просим вьюмодель обновить свои данные (На всякий случай)
    }

    private fun renderData(data: AppState) = when (data) {
        is AppState.Success -> {
            val dataList = data.data
            binding.loadingLayoutToDo.visibility = View.GONE
            adapter.setData(dataList)
        }
        is AppState.Loading -> {
            binding.loadingLayoutToDo.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            binding.loadingLayoutToDo.visibility = View.GONE

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
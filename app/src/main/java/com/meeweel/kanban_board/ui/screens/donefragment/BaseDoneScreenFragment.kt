package com.meeweel.kanban_board.ui.screens.donefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meeweel.kanban_board.databinding.FragmentBoardScreenBinding
import com.meeweel.kanban_board.databinding.FragmentDoneBinding
import com.meeweel.kanban_board.ui.screens.AppState

abstract class BaseDoneScreenFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    internal open val binding: FragmentDoneBinding
        get() {
            return _binding!!
        }

    private val viewModel: DoneScreenFragmentViewModel by lazy { // Вьюмодель
        ViewModelProvider(this).get(DoneScreenFragmentViewModel::class.java) //
    }
    private val adapter = DoneScreenFragmentRecyclerAdapter() // Адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workLivedata()
    }

    private fun workLivedata() {
        binding.doneScreenFragmentRecyclerView.adapter =
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
            binding.loadingLayoutDone.visibility = View.GONE
            adapter.setData(dataList)
        }
        is AppState.Loading -> {
            binding.loadingLayoutDone.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            binding.loadingLayoutDone.visibility = View.GONE

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
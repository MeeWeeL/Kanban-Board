package com.meeweel.kanban_board.ui.screens.boardscreen.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentInProgressBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.screens.boardscreen.BaseBoardScreenFragment


class ToDoFragment : BaseBoardScreenFragment(R.layout.fragment_to_do) {

    override val binding: FragmentInProgressBinding
        get() {
            return _binding!!
        }

    private val adapter = ToDoRecyclerAdapter() // Адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentReplacer.replace(pagePos, this)
        fabToDo()
    }

    override fun setAdapter() {
        binding.boardScreenFragmentRecyclerView.adapter =
            adapter // Приаттачиваем наш адаптер к ресайклеру, чтобы ресайклер знал, что делать
    }

    override fun setAdapterData(dataList: List<TaskModel>) {
        val list = mutableListOf<TaskModel>()
        for (item in dataList) if (item.status == Status.TO_DO) list.add(item)
        adapter.setData(list)
    }

    private fun fabToDo() {
//        binding.fabToDo.setOnClickListener {
//            Toast.makeText(context, "FabToDo", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        var board: BoardModel? = null
    }
}
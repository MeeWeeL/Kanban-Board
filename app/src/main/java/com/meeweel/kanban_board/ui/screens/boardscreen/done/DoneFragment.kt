package com.meeweel.kanban_board.ui.screens.boardscreen.done

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

class DoneFragment : BaseBoardScreenFragment(R.layout.fragment_done) {

    override val binding: FragmentInProgressBinding
        get() {
            return _binding!!
        }

    private val adapter = DoneRecyclerAdapter() // Адаптер

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInProgressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setAdapter() {
        binding.boardScreenFragmentRecyclerView.adapter =
            adapter // Приаттачиваем наш адаптер к ресайклеру, чтобы ресайклер знал, что делать
    }

    override fun setAdapterData(dataList: List<TaskModel>) {
        val list = mutableListOf<TaskModel>()
        for (item in dataList) if (item.status == Status.DONE) list.add(item)
        adapter.setData(list)
    }

    companion object {
        var board: BoardModel? = null
    }
}
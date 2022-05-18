package com.meeweel.kanban_board.ui.screens.boardscreen.done

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.FragmentBoardScreenBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.MAIN
import com.meeweel.kanban_board.ui.screens.boardscreen.BaseBoardScreenFragment
import com.meeweel.kanban_board.ui.screens.boardscreen.inprogress.InProgressRecyclerAdapter

class DoneFragment : BaseBoardScreenFragment(), View.OnTouchListener {

    override val binding: FragmentBoardScreenBinding
        get() {
            return _binding!!
        }

    private val adapter = DoneRecyclerAdapter() // Адаптер

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

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when(view){
            binding.swipe->{
                when(motionEvent.action){
                    MotionEvent.ACTION_MOVE->{
                        MAIN.navController.navigate(R.id.action_doneFragment_to_boardScreenFragment)
                    }
                }
            }
        }
        return true
    }
    companion object {
        var board: BoardModel? = null
    }
}
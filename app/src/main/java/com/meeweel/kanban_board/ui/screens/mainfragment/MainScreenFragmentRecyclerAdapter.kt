package com.meeweel.kanban_board.ui.screens.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.MainScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.ui.MAIN

class MainScreenFragmentRecyclerAdapter :
    RecyclerView.Adapter<MainScreenFragmentRecyclerAdapter.MainViewHolder>() {

    private var burgerListener: BaseMainScreenFragment.OnBurgerClickListener? = null

    private var dataList: MutableList<BoardModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            MainScreenRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class MainViewHolder(private val binding: MainScreenRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(board: BoardModel) {
            binding.apply {
                titleBoardScreen.text = board.name
                root.setOnClickListener {
                    MAIN.navController.navigate(
                        R.id.action_mainScreenFragment_to_taskScreenFragment,
                        bundleOf(ARG_BOARD_ID to board.id)
                    )
                }
                burgerMainScreen.setOnClickListener {
                    burgerListener?.onBurgerClick(it, board)
                }
            }
        }
    }

    fun setBurgerClickListener(param: BaseMainScreenFragment.OnBurgerClickListener) {
        this.burgerListener = param
    }

    fun removeListeners() {
        this.burgerListener = null
    }

    fun setData(data: List<BoardModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        const val ARG_BOARD_ID = "Board ID"
    }
}
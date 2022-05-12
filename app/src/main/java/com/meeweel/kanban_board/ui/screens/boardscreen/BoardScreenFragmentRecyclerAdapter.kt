package com.meeweel.kanban_board.ui.screens.boardscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.R
import com.meeweel.kanban_board.databinding.BoardScreenRecyclerItemBinding
import com.meeweel.kanban_board.databinding.MainScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.ui.MAIN

class BoardScreenFragmentRecyclerAdapter :
    RecyclerView.Adapter<BoardScreenFragmentRecyclerAdapter.MainViewHolder>() {

    private var dataList: MutableList<BoardModel> = mutableListOf() // Список данных, которые хотим отобразить ресайклером
                                                                    // В нашем случает тут это список досок

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = BoardScreenRecyclerItemBinding.inflate( // Создает лайаут который нужно заполнить
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) { // Вызывает заполнение лайаута
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int { // Порядковый номер в списке
        return dataList.size
    }

    inner class MainViewHolder(private val binding: BoardScreenRecyclerItemBinding) : // Возвращает заполненный лайаут итема
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BoardModel) { // Заполнение лайаута итема, здесь надо прокидывать данные на другой экран по id
            binding.apply {
                titleBoardScreen.text = data.name
                root.setOnClickListener {
                    BoardScreenFragment.board = data
                    MAIN.navController.navigate(R.id.action_mainScreenFragment_to_boardScreenFragment)
                }
            }
        }
    }

    fun setData(data: List<BoardModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
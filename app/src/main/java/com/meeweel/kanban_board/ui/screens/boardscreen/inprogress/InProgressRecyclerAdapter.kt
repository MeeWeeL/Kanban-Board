package com.meeweel.kanban_board.ui.screens.boardscreen.inprogress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.BoardScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.TaskModel

class InProgressRecyclerAdapter :
    RecyclerView.Adapter<InProgressRecyclerAdapter.MainViewHolder>() {

    private var dataList: MutableList<TaskModel> = mutableListOf() // Список данных, которые хотим отобразить ресайклером
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

        fun bind(data: TaskModel) { // Заполнение лайаута итема, здесь надо прокидывать данные на другой экран по id
            binding.apply {
                titleBoardScreen.text = data.name
            }
        }
    }

    fun setData(data: List<TaskModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
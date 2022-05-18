package com.meeweel.kanban_board.ui.screens.todoscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.ToDoScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel

class ToDoScreenFragmentRecyclerAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ToDoScreenFragmentRecyclerAdapter.MainViewHolder>() {

    private var dataList: MutableList<BoardModel> =
        mutableListOf() // Список данных, которые хотим отобразить ресайклером
    // В нашем случает тут это список досок

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding =
            ToDoScreenRecyclerItemBinding.inflate( // Создает лайаут который нужно заполнить
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) { // Вызывает заполнение лайаута
        holder.bind(dataList[position], listener)
    }

    override fun getItemCount(): Int { // Порядковый номер в списке
        return dataList.size
    }

    inner class MainViewHolder(private val binding: ToDoScreenRecyclerItemBinding) : // Возвращает заполненный лайаут итема
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BoardModel, listener: OnItemClickListener) { // Заполнение лайаута итема, здесь надо прокидывать данные на другой экран по id
            binding.apply {
                titleToDoScreen.text = data.name
                root.setOnClickListener {
                    ToDoFragment.board = data
                }
                listener.onItemClick(recyclerItemToDoScreen)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View)
    }

    fun setData(data: List<BoardModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
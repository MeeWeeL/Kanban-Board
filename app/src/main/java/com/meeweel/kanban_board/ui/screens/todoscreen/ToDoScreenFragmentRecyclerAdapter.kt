package com.meeweel.kanban_board.ui.screens.todoscreen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.ToDoScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel

class ToDoScreenFragmentRecyclerAdapter :
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
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int { // Порядковый номер в списке
        return dataList.size
    }

    inner class MainViewHolder(private val binding: ToDoScreenRecyclerItemBinding) : // Возвращает заполненный лайаут итема
        RecyclerView.ViewHolder(binding.root) {
        private val toDoFragment = ToDoFragment()
        fun bind(data: BoardModel) { // Заполнение лайаута итема, здесь надо прокидывать данные на другой экран по id
            binding.apply {
                titleToDoScreen.text = data.name
                root.setOnClickListener {
                    ToDoFragment.board = data
                }
                recyclerItemToDoScreen.setOnClickListener {
                    toDoFragment.popupMenu(root)
                }
            }
        }
    }

    fun setData(data: List<BoardModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
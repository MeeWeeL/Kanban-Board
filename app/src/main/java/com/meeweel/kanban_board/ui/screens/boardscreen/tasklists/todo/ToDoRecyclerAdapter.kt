package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.ToDoScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.OnBurgerClickListener

class ToDoRecyclerAdapter() :
    RecyclerView.Adapter<ToDoRecyclerAdapter.MainViewHolder>() {

    private var burgerListener: OnBurgerClickListener? =
        null
    private var dataList: MutableList<TaskModel> =
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
        fun bind(data: TaskModel) { // Заполнение лайаута итема, здесь надо прокидывать данные на другой экран по id
            binding.apply {
                titleToDoScreen.text = data.name
                burgerToDo.setOnClickListener{
                    burgerListener?.onBurgerClick(it as AppCompatImageButton)
                }
            }
        }
    }


    fun setBurgerClickListener(param: OnBurgerClickListener) {
        this.burgerListener = param
    }
    fun removeClickListeners() {
        this.burgerListener = null
    }

    fun setData(data: List<TaskModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
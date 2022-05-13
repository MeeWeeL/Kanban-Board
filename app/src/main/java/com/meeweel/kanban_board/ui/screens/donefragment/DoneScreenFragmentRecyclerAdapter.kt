package com.meeweel.kanban_board.ui.screens.donefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.BoardScreenRecyclerItemBinding
import com.meeweel.kanban_board.databinding.DoneScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.BoardModel

class DoneScreenFragmentRecyclerAdapter :
    RecyclerView.Adapter<DoneScreenFragmentRecyclerAdapter.MainViewHolder>() {

    private var dataList: MutableList<BoardModel> = mutableListOf() // Список данных, которые хотим отобразить ресайклером
                                                                    // В нашем случает тут это список досок

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = DoneScreenRecyclerItemBinding.inflate( // Создает лайаут который нужно заполнить
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

    inner class MainViewHolder(private val binding: DoneScreenRecyclerItemBinding) : // Возвращает заполненный лайаут итема
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BoardModel) { // Заполнение лайаута итема, здесь надо прокидывать данные на другой экран по id
            binding.apply {
                titleDoneScreen.text = data.name
                root.setOnClickListener {
                    DoneFragment.board = data
                }
            }
        }
    }

    fun setData(data: List<BoardModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
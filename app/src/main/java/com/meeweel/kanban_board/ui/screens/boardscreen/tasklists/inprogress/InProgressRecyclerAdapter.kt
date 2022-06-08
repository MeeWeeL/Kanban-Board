package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.inprogress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.BoardScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.OnBurgerClickListener

class InProgressRecyclerAdapter :
    RecyclerView.Adapter<InProgressRecyclerAdapter.MainViewHolder>() {

    private var itemListener: InProgressFragment.OnTaskClickListener? = null

    private var burgerListener: OnBurgerClickListener? =
        null // Этот бургер будем навешивать на бургер
    // Настраивается он из фрагмента, через функцию setBurgerClickListener() в этом адаптере (И зануляется тоже)
    // Сам этот интерфейс создан тоже во фрагменте

    private var dataList: MutableList<TaskModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = BoardScreenRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MainViewHolder(private val binding: BoardScreenRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TaskModel) {
            binding.apply {
                titleBoardScreen.text = data.name
                burger.setOnClickListener {
                    burgerListener?.onBurgerClick(it as AppCompatImageButton) // То есть при нажатии будет срабатывать то, что мы указали методом setBurgerClickListener()
                }
                root.setOnClickListener {
                    itemListener?.showTaskSheet(data)
                }
            }
        }
    }

    fun setBurgerClickListener(param: OnBurgerClickListener) {
        this.burgerListener = param
    }

    fun setItemListener(itemListener: InProgressFragment.OnTaskClickListener) {
        this.itemListener = itemListener
    }

    fun removeClickListeners() {
        this.burgerListener = null
        this.itemListener = null
    }

    fun setData(data: List<TaskModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
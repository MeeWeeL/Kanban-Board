package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.BoardScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.TaskModel

class BaseBoardScreenAdapter :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var itemListener: BaseBoardScreenFragment.OnTaskClickListener? = null
    private var itemLongListener: BaseBoardScreenFragment.OnLongTaskClickListener? = null
    private var burgerListener: OnBurgerClickListener? = null

    private var dataList: MutableList<TaskModel> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = BoardScreenRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MainViewHolder(private val binding: BoardScreenRecyclerItemBinding) :
        BaseViewHolder(binding.root) {

        override fun bind(data: TaskModel) {
            binding.apply {
                titleBoardScreen.text = data.name
                burger.setOnClickListener {
                    burgerListener?.onBurgerClick(it as AppCompatImageButton)
                }
                root.setOnClickListener {
                    itemListener?.showTaskSheet(data)
                }
                root.setOnLongClickListener {
                    itemLongListener?.showTaskEditSheet(data)
                    true
                }
            }
        }
    }

    fun setBurgerClickListener(param: OnBurgerClickListener) {
        this.burgerListener = param
    }

    fun setItemListener(itemListener: BaseBoardScreenFragment.OnTaskClickListener) {
        this.itemListener = itemListener
    }

    fun setLongItemListener(itemLongListener: BaseBoardScreenFragment.OnLongTaskClickListener) {
        this.itemLongListener = itemLongListener
    }

    fun removeClickListeners() {
        this.burgerListener = null
        this.itemListener = null
        this.itemLongListener = null
    }

    fun setData(data: List<TaskModel>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}
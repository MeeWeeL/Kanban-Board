package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.databinding.BoardScreenRecyclerItemBinding
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.BaseTaskListFragment

class BaseBoardScreenAdapter :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var itemListener: BaseTaskListFragment.OnTaskClickListener? = null
    private var itemLongListener: BaseTaskListFragment.OnLongTaskClickListener? = null
    private var burgerListener: BaseTaskListFragment.OnBurgerClickListener? = null

    private var dataList: MutableList<TaskModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = BoardScreenRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(dataList[position])

    override fun getItemCount(): Int = dataList.size

    inner class MainViewHolder(private val itemBinding: BoardScreenRecyclerItemBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun bind(task: TaskModel) {
            itemBinding.apply {
                titleBoardScreen.text = task.name
                burger.setOnClickListener {
                    burgerListener?.onBurgerClick(itemBinding.burger, task)
                }
                root.setOnClickListener {
                    itemListener?.showTaskSheet(task)
                }
                root.setOnLongClickListener {
                    itemLongListener?.showTaskEditSheet(task)
                    true
                }
            }
        }
    }

    fun setBurgerClickListener(param: BaseTaskListFragment.OnBurgerClickListener) {
        this.burgerListener = param
    }

    fun setItemListener(itemListener: BaseTaskListFragment.OnTaskClickListener) {
        this.itemListener = itemListener
    }

    fun setLongItemListener(itemLongListener: BaseTaskListFragment.OnLongTaskClickListener) {
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
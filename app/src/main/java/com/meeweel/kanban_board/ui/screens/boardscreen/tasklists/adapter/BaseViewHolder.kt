package com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.core.basemodels.TaskModel

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(task: TaskModel)
}
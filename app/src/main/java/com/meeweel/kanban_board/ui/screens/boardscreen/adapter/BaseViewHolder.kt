package com.meeweel.kanban_board.ui.screens.boardscreen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meeweel.kanban_board.domain.basemodels.TaskModel

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(task: TaskModel)
}
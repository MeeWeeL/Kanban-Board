package com.meeweel.kanban_board.data

import androidx.room.PrimaryKey

data class BoardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    val host: String,
    val key: String,
    val toDoList: MutableList<TaskModel> = mutableListOf()
)
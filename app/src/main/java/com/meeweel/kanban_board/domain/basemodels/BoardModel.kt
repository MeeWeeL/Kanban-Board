package com.meeweel.kanban_board.domain.basemodels

data class BoardModel(
    val id: Int,
    var name: String,
    val host: String,
    val key: String,
    val toDoList: MutableList<TaskModel> = mutableListOf()
)
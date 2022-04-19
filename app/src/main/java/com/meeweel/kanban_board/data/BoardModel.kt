package com.meeweel.kanban_board.data

data class BoardModel(
    val id: Int,
    val name: String,
    val host: String,
    val toDoList: List<TaskModel>,
    val key: String
)
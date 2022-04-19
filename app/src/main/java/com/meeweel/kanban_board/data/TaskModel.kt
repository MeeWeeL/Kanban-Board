package com.meeweel.kanban_board.data

data class TaskModel(
    val id: Int,
    val name: String,
    val description: String,
    val status: Status,
    val performer: String
)
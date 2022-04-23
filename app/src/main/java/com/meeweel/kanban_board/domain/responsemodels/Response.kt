package com.meeweel.kanban_board.domain.responsemodels

data class Response(
    val id: Int,
    var name: String,
    val host: String,
    val key: String,
    val toDoList: List<TaskResponse> = listOf()
)
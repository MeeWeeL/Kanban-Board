package com.meeweel.kanban_board.domain.responsemodels

data class TaskResponse(
    val id: Int,
    var name: String = "Task",
    var description: String?,
    var status: Int,
    var priority: Int,
    var performer: String = "None"
)
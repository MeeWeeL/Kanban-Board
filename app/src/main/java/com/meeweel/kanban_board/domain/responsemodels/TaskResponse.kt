package com.meeweel.kanban_board.domain.responsemodels

data class TaskResponse(
    val id: Int,
    var title: String = "Task",
    var description: String?,
    var task_status: Int,
    var task_priority: Int,
    var task_performer: String = "None"
)
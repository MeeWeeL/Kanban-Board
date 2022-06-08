package com.meeweel.kanban_board.domain.responsemodels

import com.meeweel.kanban_board.domain.basemodels.Priority
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel

data class TaskResponse(
    val id: Int,
    var title: String = "Task",
    var description: String?,
    var task_status: Int,
    var task_priority: Int,
    var task_performer: String = "None"
)

fun TaskResponse.toTaskModel() = TaskModel(
    id = this.id,
    name = this.title,
    description = this.description ?: "",
    status = when (this.task_status) {
        1 -> Status.IN_PROGRESS
        2 -> Status.DONE
        else -> Status.TO_DO
    },
    priority = when (this.task_priority) {
        1 -> Priority.GREEN
        2 -> Priority.YELLOW
        3 -> Priority.ORANGE
        4 -> Priority.RED
        else -> Priority.NONE
    },
    performer = this.task_performer,
)

fun List<TaskResponse>.toTaskModelList() = this.map { it.toTaskModel() }
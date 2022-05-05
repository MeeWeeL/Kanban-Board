package com.meeweel.kanban_board.domain

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.Priority
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.responsemodels.Response
import com.meeweel.kanban_board.domain.responsemodels.TaskResponse

fun convertResponseListToBoardModelList(responseList: List<Response>): List<BoardModel> {
    return responseList.map {
        BoardModel(
            it.board_id,
            it.board_title,
            it.board_host,
            it.board_key,
            convertTaskResponseListToTaskModelList(it.tasks)
        )
    }
}

fun convertTaskResponseListToTaskModelList(taskResponseList: List<TaskResponse>): MutableList<TaskModel> {
    return taskResponseList.map {
        TaskModel(
            it.id,
            it.title,
            it.description ?: "None",
            when (it.task_status) {
                1 -> Status.IN_PROGRESS
                2 -> Status.DONE
                else -> Status.TO_DO
            },
            when (it.task_priority) {
                1 -> Priority.GREEN
                2 -> Priority.YELLOW
                3 -> Priority.ORANGE
                4 -> Priority.RED
                else -> Priority.NONE
            },
            it.task_performer
        )
    }.toMutableList()
}
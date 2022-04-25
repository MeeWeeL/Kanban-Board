package com.meeweel.kanban_board.domain

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.Priority
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.responsemodels.Response
import com.meeweel.kanban_board.domain.responsemodels.TaskResponse

fun convertResponseListToBoardModelList(responseList: List<Response>) : List<BoardModel> {
    return responseList.map {
        BoardModel(it.id, it.name, it.host, it.key, convertTaskResponseListToTaskModelList(it.toDoList))
    }
}

fun convertTaskResponseListToTaskModelList(taskResponseList: List<TaskResponse>) : MutableList<TaskModel> {
    return taskResponseList.map {
        TaskModel(
            it.id,
            it.name,
            it.description ?: "None",
            when(it.status){
                1 -> Status.IN_PROGRESS
                2 -> Status.DONE
                else -> Status.TO_DO
            },
            when(it.priority){
                1 -> Priority.GREEN
                2 -> Priority.YELLOW
                3 -> Priority.ORANGE
                4 -> Priority.RED
                else -> Priority.NONE
            },
            it.performer
        )
    }.toMutableList()
}
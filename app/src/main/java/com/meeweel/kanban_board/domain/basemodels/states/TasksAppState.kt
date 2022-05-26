package com.meeweel.kanban_board.domain.basemodels.states

import com.meeweel.kanban_board.domain.basemodels.TaskModel

sealed class TasksAppState {
    data class Success(val data: List<TaskModel>) : TasksAppState()
    class Error(val error: Throwable) : TasksAppState()
    object Loading : TasksAppState()
}
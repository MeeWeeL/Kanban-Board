package com.meeweel.core.basemodels.states

import com.meeweel.core.basemodels.TaskModel

sealed class TasksAppState {
    data class Success(val data: List<TaskModel>) : TasksAppState()
    class Error(val error: Throwable) : TasksAppState()
    object Loading : TasksAppState()
}
package com.meeweel.core.basemodels.states

import com.meeweel.core.basemodels.TaskModel

sealed class BoardState {
    data class Success(val data: List<TaskModel>) : BoardState()
    class Error(val error: Throwable) : BoardState()
    object Loading : BoardState()
}
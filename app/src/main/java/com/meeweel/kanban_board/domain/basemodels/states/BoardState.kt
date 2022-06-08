package com.meeweel.kanban_board.domain.basemodels.states

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel

sealed class BoardState {
    data class Success(val data: List<TaskModel>) : BoardState()
    class Error(val error: Throwable) : BoardState()
    object Loading : BoardState()
}
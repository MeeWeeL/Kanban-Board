package com.meeweel.kanban_board.domain.basemodels.states

import com.meeweel.kanban_board.domain.basemodels.BoardModel

sealed class BoardsAppState {
    data class Success(val data: List<BoardModel>) : BoardsAppState()
    class Error(val error: Throwable) : BoardsAppState()
    object Loading : BoardsAppState()
}

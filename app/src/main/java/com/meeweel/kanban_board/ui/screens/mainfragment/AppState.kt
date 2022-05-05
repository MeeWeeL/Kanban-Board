package com.meeweel.kanban_board.ui.screens.mainfragment

import com.meeweel.kanban_board.domain.basemodels.BoardModel

sealed class AppState {
    data class Success(val data: List<BoardModel>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

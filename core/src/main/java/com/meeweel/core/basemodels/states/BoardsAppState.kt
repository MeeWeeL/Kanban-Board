package com.meeweel.core.basemodels.states

import com.meeweel.core.basemodels.BoardModel

sealed class BoardsAppState {
    data class Success(val data: List<BoardModel>) : BoardsAppState()
    class Error(val error: Throwable) : BoardsAppState()
    object Loading : BoardsAppState()
}

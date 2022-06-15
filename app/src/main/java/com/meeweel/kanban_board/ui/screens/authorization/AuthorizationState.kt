package com.meeweel.kanban_board.ui.screens.authorization

import com.meeweel.data.room.UserEntity

sealed class AuthorizationState {
    data class Success(val data: UserEntity) : AuthorizationState()
    class Error(val error: Throwable) : AuthorizationState()
    object Loading : AuthorizationState()
    object Done : AuthorizationState()
}
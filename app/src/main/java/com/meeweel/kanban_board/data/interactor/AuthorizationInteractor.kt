package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.room.UserEntity
import io.reactivex.rxjava3.core.Single

interface AuthorizationInteractor {

    fun logOut()
    fun checkAuthorization() : List<UserEntity>
    fun saveAuthorization(login: String, password: String)
    fun signIn(login: String, password: String) : Single<Boolean>
    fun signUp(login: String, password: String) : Single<Boolean>
}
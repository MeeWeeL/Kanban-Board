package com.meeweel.data.interactor

import com.meeweel.kanban_board.data.room.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthorizationInteractor {

    fun logOut(): Completable
    fun checkAuthorization() : List<UserEntity>
    fun saveAuthorization(login: String, password: String)
    fun signIn(login: String, password: String) : Single<Boolean>
    fun signUp(login: String, password: String) : Single<Boolean>
}
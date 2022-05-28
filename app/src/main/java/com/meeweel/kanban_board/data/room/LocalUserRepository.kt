package com.meeweel.kanban_board.data.room

import io.reactivex.rxjava3.core.Single

interface LocalUserRepository {

    fun logOut()
    fun checkAuthorization() : Single<List<UserEntity>>
    fun getUser() : List<UserEntity>
    fun saveAuthorization(login: String, password: String)
}
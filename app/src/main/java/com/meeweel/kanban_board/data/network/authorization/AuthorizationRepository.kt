package com.meeweel.kanban_board.data.network.authorization

import io.reactivex.rxjava3.core.Single

interface AuthorizationRepository {

    fun signIn(login: String, password: String) : Single<Boolean>
    fun signUp(login: String, password: String) : Single<Boolean>
}
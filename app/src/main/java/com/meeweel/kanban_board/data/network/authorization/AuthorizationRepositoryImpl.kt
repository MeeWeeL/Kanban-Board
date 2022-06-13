package com.meeweel.kanban_board.data.network.authorization

import io.reactivex.rxjava3.core.Single

class AuthorizationRepositoryImpl(private val service: AuthorizationApi) :
    AuthorizationRepository {

    override fun signIn(login: String, password: String): Single<Boolean> =
        service.signIn(login, password)

    override fun signUp(login: String, password: String): Single<Boolean> =
        service.signUp(login, password)
}
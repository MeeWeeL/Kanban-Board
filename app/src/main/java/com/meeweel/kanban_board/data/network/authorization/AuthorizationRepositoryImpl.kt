package com.meeweel.kanban_board.data.network.authorization

import io.reactivex.rxjava3.core.Single

class AuthorizationRepositoryImpl(private val service: AuthorizationApi = AuthorizationService().getService()) :
    AuthorizationRepository {

    override fun signIn(login: String, password: String): Single<Boolean> {
        return service.signIn(login, password)
    }

    override fun signUp(login: String, password: String): Single<Boolean> {
        return service.signUp(login, password)
    }
}
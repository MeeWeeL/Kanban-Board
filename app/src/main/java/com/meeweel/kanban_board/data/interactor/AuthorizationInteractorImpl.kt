package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.network.autorization.AuthorizationRepository
import com.meeweel.kanban_board.data.network.autorization.AuthorizationRepositoryImpl
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.data.room.LocalUserRepositoryImpl
import com.meeweel.kanban_board.data.room.UserEntity
import io.reactivex.rxjava3.core.Single

class AuthorizationInteractorImpl(
    private val remoteRepo: AuthorizationRepository = AuthorizationRepositoryImpl(),
    private val localRepo: LocalUserRepository = LocalUserRepositoryImpl()
) : AuthorizationInteractor {

    override fun logOut() {
        localRepo.logOut()
    }

    override fun checkAuthorization(): List<UserEntity> {
        return localRepo.getUser()
    }

    override fun saveAuthorization(login: String, password: String) {
        localRepo.saveAuthorization(login, password)
    }

    override fun signIn(login: String, password: String): Single<Boolean> {
        return remoteRepo.signIn(login, password)
    }

    override fun signUp(login: String, password: String): Single<Boolean> {
        return remoteRepo.signUp(login, password)
    }
}
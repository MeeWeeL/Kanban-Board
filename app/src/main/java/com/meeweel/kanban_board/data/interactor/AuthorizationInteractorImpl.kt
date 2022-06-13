package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.network.authorization.AuthorizationRepository
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.data.room.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AuthorizationInteractorImpl(
    private val remoteRepo: AuthorizationRepository,
    private val localRepo: LocalUserRepository
) : AuthorizationInteractor {

    override fun logOut(): Completable = localRepo.logOut()

    override fun checkAuthorization(): List<UserEntity> = localRepo.getUser()

    override fun saveAuthorization(login: String, password: String) =
        localRepo.saveAuthorization(login, password)

    override fun signIn(login: String, password: String): Single<Boolean> =
        remoteRepo.signIn(login, password)

    override fun signUp(login: String, password: String): Single<Boolean> =
        remoteRepo.signUp(login, password)
}
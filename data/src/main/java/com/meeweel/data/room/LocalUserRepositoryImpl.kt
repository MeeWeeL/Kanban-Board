package com.meeweel.data.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class LocalUserRepositoryImpl(
    private val localRepository: UserEntityDao
) : LocalUserRepository {

    override fun logOut(): Completable = localRepository.logOut()

    override fun checkAuthorization(): Single<List<UserEntity>> = localRepository.getUserData()

    override fun getUser(): List<UserEntity> = localRepository.getUser()

    override fun saveAuthorization(login: String, password: String) =
        localRepository.saveUserData(UserEntity(login, password))
}
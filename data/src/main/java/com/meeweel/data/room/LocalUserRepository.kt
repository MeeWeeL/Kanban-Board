package com.meeweel.data.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface LocalUserRepository {

    fun logOut(): Completable
    fun checkAuthorization(): Single<List<UserEntity>>
    fun getUser(): List<UserEntity>
    fun saveAuthorization(login: String, password: String)
}
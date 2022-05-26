package com.meeweel.kanban_board.data.room

import androidx.room.Room
import com.meeweel.kanban_board.app.App.AppInstance.context
import io.reactivex.rxjava3.core.Single

class LocalUserRepositoryImpl(
    private val localRepository: UserEntityDao = Room.databaseBuilder(
        context,
        UserEntityDataBases::class.java,
        "UserRepo.db"
    ).allowMainThreadQueries().build().entityDao()
) : LocalUserRepository {

    override fun logOut() {
        localRepository.logOut()
    }

    override fun checkAuthorization(): Single<List<UserEntity>> {
        return localRepository.getUserData()
    }

    override fun getUser(): List<UserEntity> {
        return localRepository.getUser()
    }

    override fun saveAuthorization(login: String, password: String) {
        localRepository.saveUserData(UserEntity(login, password))
    }
}
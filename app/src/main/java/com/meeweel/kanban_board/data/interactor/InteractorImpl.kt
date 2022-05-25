package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.network.boards.RemoteRepository
import com.meeweel.kanban_board.data.network.boards.RemoteRepositoryImpl
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.data.room.LocalUserRepositoryImpl
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import io.reactivex.rxjava3.core.Single

class InteractorImpl(
    private val remoteRepo: RemoteRepository = RemoteRepositoryImpl(),
    private val localRepo: LocalUserRepository = LocalUserRepositoryImpl()
) : Interactor {


    override fun getBoards(): Single<List<BoardModel>> {
        val user = localRepo.getUser()[0]
        return remoteRepo.getBoards(user.login, user.password) // Получить с сервера
    }
}
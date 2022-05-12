package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.network.RemoteRepository
import com.meeweel.kanban_board.data.network.RemoteRepositoryImpl
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.FakeRepository
import io.reactivex.rxjava3.core.Single

class InteractorImpl(private val remoteRepo: RemoteRepository = RemoteRepositoryImpl()) : Interactor {
    override fun getBoards(): Single<List<BoardModel>> {
//        return FakeRepository().getBoards() // Получить из фейк репозитория
        return remoteRepo.getBoards() // Получить с сервера
    }
}
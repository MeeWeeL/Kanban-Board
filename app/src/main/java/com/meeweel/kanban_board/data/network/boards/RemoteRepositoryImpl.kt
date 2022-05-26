package com.meeweel.kanban_board.data.network.boards

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.convertResponseListToBoardModelList
import io.reactivex.rxjava3.core.Single

class RemoteRepositoryImpl(private val service: KanbanApi = BoardsService().getService()) :
    RemoteRepository {
    override fun getFakeBoards(): Single<List<BoardModel>> {
        return service.getFakeBoards().map {
            convertResponseListToBoardModelList(it)
        }
    }
    override fun getBoards(login: String, password: String): Single<List<BoardModel>> {
        return service.getBoards(login, password).map {
            convertResponseListToBoardModelList(it)
        }
    }
}
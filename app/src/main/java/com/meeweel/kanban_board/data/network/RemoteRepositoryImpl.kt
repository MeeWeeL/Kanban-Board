package com.meeweel.kanban_board.data.network

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.convertResponseListToBoardModelList
import io.reactivex.rxjava3.core.Single

class RemoteRepositoryImpl(private val service: KanbanApi = RetrofitService().getService()) : RemoteRepository {
    override fun getFakeBoards(): Single<List<BoardModel>> {
        return service.getFakeBoards().map {
            convertResponseListToBoardModelList(it)
        }
    }

    override fun getBoards(): Single<List<BoardModel>> {
        return service.getBoards().map {
            convertResponseListToBoardModelList(it)
        }
    }
}
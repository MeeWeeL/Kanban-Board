package com.meeweel.kanban_board.data.network

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {

    fun getFakeBoards() : Single<List<BoardModel>>
}
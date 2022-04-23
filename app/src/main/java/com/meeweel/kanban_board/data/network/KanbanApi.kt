package com.meeweel.kanban_board.data.network

import com.meeweel.kanban_board.domain.responsemodels.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface KanbanApi {

    @GET("api.php?q=get_fake_repository")
    fun getFakeBoards() : Single<List<Response>>
}
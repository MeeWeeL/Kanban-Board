package com.meeweel.kanban_board.data.network.boards

import com.meeweel.kanban_board.domain.responsemodels.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KanbanApi {

    @GET("api.php?q=get_fake_repository")
    fun getFakeBoards() : Single<List<Response>>

    @GET("api.php?q=get_boards")
    fun getBoards(
        @Query("my_login") login: String,
        @Query("my_password") password: String
    ) : Single<List<Response>>
}
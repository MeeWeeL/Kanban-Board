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

    @GET("api.php?q=get_board_by_id")
    fun getBoardById(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("board_id") boardId: Int
    ) : Single<Response>

    @GET("api.php?q=add_task")
    fun addTask(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("board_id") boardId: Int,
        @Query("task_title") title: String,
        @Query("task_description") description: String
    ) : Single<Boolean>

    @GET("api.php?q=add_board")
    fun addBoard(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("board_title") title: String
    ) : Single<Boolean>

    @GET("api.php?q=update_task")
    fun updateTask(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("task_id") taskId: Int,
        @Query("task_title") title: String,
        @Query("task_description") description: String,
        @Query("task_status") status: Int,
        @Query("task_priority") priority: Int,
        @Query("task_performer") performer: String
    ) : Single<Boolean>

    @GET("api.php?q=update_board")
    fun updateBoard(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("board_title") title: String,
        @Query("board_id") boardId: Int
    ) : Single<Boolean>

    @GET("api.php?q=delete_task")
    fun deleteTask(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("task_id") taskId: Int
    ) : Single<Boolean>

    @GET("api.php?q=delete_board")
    fun deleteBoard(
        @Query("my_login") login: String,
        @Query("my_password") password: String,
        @Query("board_id") boardId: Int
    ) : Single<Boolean>

    @GET("api.php?q=delete_user")
    fun deleteUser(
        @Query("my_login") login: String,
        @Query("my_password") password: String
    ) : Single<Boolean>

    @GET("api.php?q=get_board_key")
    fun createBoardKey(
        @Query("board_id") boardId: Int,
        @Query("my_login") login: String,
        @Query("my_password") password: String
    ) : Single<String>

    @GET("api.php?q=add_board_by_key")
    fun addBoardByKey(
        @Query("board_key") boardKey: String,
        @Query("my_login") login: String,
        @Query("my_password") password: String
    ) : Single<Boolean>
}
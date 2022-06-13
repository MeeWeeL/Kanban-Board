package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Interactor {

    fun getUserLogin(): Single<String>
    fun getBoards(): Single<List<BoardModel>>
    fun getBoardById(boardId: Int): Single<BoardModel>

    fun addTask(boardId: Int, task: TaskModel): Single<Boolean>
    fun addBoard(boardTitle: String): Single<Boolean>

    fun changeTask(task: TaskModel): Single<Boolean>
    fun changeBoard(board: BoardModel): Single<Boolean>

    fun deleteTask(taskId: Int): Single<Boolean>
    fun deleteBoard(boardId: Int): Single<Boolean>
    fun deleteUser(): Single<Boolean>

    fun createBoardKey(boardId: Int): Single<String>
    fun addBoardByKey(boardKey: String): Single<Boolean>

    fun logOut(): Completable
}
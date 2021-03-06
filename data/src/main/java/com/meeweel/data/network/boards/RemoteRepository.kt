package com.meeweel.data.network.boards

import com.meeweel.core.basemodels.BoardModel
import com.meeweel.core.basemodels.TaskModel
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {

    fun getFakeBoards(): Single<List<BoardModel>>
    fun getBoards(login: String, password: String): Single<List<BoardModel>>
    fun getBoardById(boardId: Int, login: String, password: String): Single<BoardModel>

    fun addTask(boardId: Int, task: TaskModel, login: String, password: String) : Single<Boolean>
    fun addBoard(boardTitle: String, login: String, password: String) : Single<Boolean>

    fun changeTask(task: TaskModel, login: String, password: String) : Single<Boolean>
    fun changeBoard(board: BoardModel, login: String, password: String) : Single<Boolean>

    fun deleteTask(taskId: Int, login: String, password: String) : Single<Boolean>
    fun deleteBoard(boardId: Int, login: String, password: String) : Single<Boolean>
    fun deleteUser(login: String, password: String) : Single<Boolean>

    fun createBoardKey(boardId: Int, login: String, password: String) : Single<String>
    fun addBoardByKey(boardKey: String, login: String, password: String) : Single<Boolean>
}
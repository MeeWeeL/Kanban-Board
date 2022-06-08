package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import io.reactivex.rxjava3.core.Single

interface Interactor {

    fun getBoards(): Single<List<BoardModel>>

    fun addTask(boardId: Int, task: TaskModel) : Single<Boolean>
    fun addBoard(boardTitle: String) : Single<Boolean>

    fun changeTask(task: TaskModel) : Single<Boolean>
    fun changeBoard(board: BoardModel) : Single<Boolean>

    fun deleteTask(taskId: Int) : Single<Boolean>
    fun deleteBoard(boardId: Int) : Single<Boolean>
    fun deleteUser() : Single<Boolean>
}
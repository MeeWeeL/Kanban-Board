package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import io.reactivex.rxjava3.core.Single

interface Interactor {

    fun getBoards(): Single<List<BoardModel>>

//    fun createTask(): TaskModel
//    fun updateTask(): TaskModel
//    fun deleteTask(): String

//    fun addBoard(): BoardModel
//    fun createBoard(): BoardModel
//    fun updateBoard(): BoardModel
//    fun deleteBoard(): String
}
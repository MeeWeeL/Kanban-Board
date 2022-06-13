package com.meeweel.kanban_board.data.network.boards

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.responsemodels.toBoardModel
import com.meeweel.kanban_board.domain.responsemodels.toBoardModelList
import io.reactivex.rxjava3.core.Single

class RemoteRepositoryImpl(private val service: KanbanApi) :
    RemoteRepository {

    override fun getFakeBoards(): Single<List<BoardModel>> =
        service.getFakeBoards().map { it.toBoardModelList() }

    override fun getBoards(login: String, password: String): Single<List<BoardModel>> =
        service.getBoards(login, password).map { it.toBoardModelList() }

    override fun getBoardById(boardId: Int, login: String, password: String): Single<BoardModel> =
        service.getBoardById(login, password, boardId).map { it.toBoardModel() }

    override fun addTask(
        boardId: Int,
        task: TaskModel,
        login: String,
        password: String
    ): Single<Boolean> = service.addTask(login, password, boardId, task.name, task.description)

    override fun addBoard(boardTitle: String, login: String, password: String): Single<Boolean> =
        service.addBoard(login, password, boardTitle)

    override fun changeTask(task: TaskModel, login: String, password: String): Single<Boolean> =
        service.updateTask(
            login,
            password,
            task.id,
            task.name,
            task.description,
            task.status.int,
            task.priority.int,
            task.performer
        )

    override fun changeBoard(board: BoardModel, login: String, password: String): Single<Boolean> =
        service.updateBoard(login, password, board.name, board.id)

    override fun deleteTask(taskId: Int, login: String, password: String): Single<Boolean> =
        service.deleteTask(login, password, taskId)

    override fun deleteBoard(boardId: Int, login: String, password: String): Single<Boolean> =
        service.deleteBoard(login, password, boardId)

    override fun deleteUser(login: String, password: String): Single<Boolean> =
        service.deleteUser(login, password)

    override fun createBoardKey(boardId: Int, login: String, password: String): Single<String> =
        service.createBoardKey(boardId, login, password)

    override fun addBoardByKey(boardKey: String, login: String, password: String): Single<Boolean> =
        service.addBoardByKey(boardKey, login, password)
}
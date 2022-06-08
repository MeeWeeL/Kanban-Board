package com.meeweel.kanban_board.data.network.boards

import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.convertResponseListToBoardModelList
import io.reactivex.rxjava3.core.Single

class RemoteRepositoryImpl(private val service: KanbanApi = BoardsService().getService()) :
    RemoteRepository {
    override fun getFakeBoards(): Single<List<BoardModel>> {
        return service.getFakeBoards().map {
            convertResponseListToBoardModelList(it)
        }
    }
    override fun getBoards(login: String, password: String): Single<List<BoardModel>> {
        return service.getBoards(login, password).map {
            convertResponseListToBoardModelList(it)
        }
    }

    override fun addTask(boardId: Int, task: TaskModel, login: String, password: String): Single<Boolean> {
        return service.addTask(login, password, boardId, task.name, task.description)
    }

    override fun addBoard(boardTitle: String, login: String, password: String): Single<Boolean> {
        return service.addBoard(login, password, boardTitle)
    }

    override fun changeTask(task: TaskModel, login: String, password: String): Single<Boolean> {
        return service.updateTask(login, password, task.id, task.name, task.description, task.status.int, task.priority.int, task.performer)
    }

    override fun changeBoard(board: BoardModel, login: String, password: String): Single<Boolean> {
        return service.updateBoard(login, password, board.name, board.id)
    }

    override fun deleteTask(taskId: Int, login: String, password: String): Single<Boolean> {
        return service.deleteTask(login, password, taskId)
    }

    override fun deleteBoard(boardId: Int, login: String, password: String): Single<Boolean> {
        return service.deleteBoard(login, password, boardId)
    }

    override fun deleteUser(login: String, password: String): Single<Boolean> {
        return service.deleteUser(login, password)
    }
}
package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.network.boards.RemoteRepository
import com.meeweel.kanban_board.data.network.boards.RemoteRepositoryImpl
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.data.room.LocalUserRepositoryImpl
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import io.reactivex.rxjava3.core.Single

class InteractorImpl(
    private val remoteRepo: RemoteRepository = RemoteRepositoryImpl(),
    private val localRepo: LocalUserRepository = LocalUserRepositoryImpl()
) : Interactor {

    private val user by lazy {
        localRepo.getUser()[0]
    }

    override fun getBoards(): Single<List<BoardModel>> {
        return remoteRepo.getBoards(user.login, user.password)
    }

    override fun addTask(boardId: Int, task: TaskModel): Single<Boolean> {
        return remoteRepo.addTask(boardId, task, user.login, user.password)
    }

    override fun addBoard(boardTitle: String): Single<Boolean> {
        return remoteRepo.addBoard(boardTitle, user.login, user.password)
    }

    override fun changeTask(task: TaskModel): Single<Boolean> {
        return remoteRepo.changeTask(task, user.login, user.password)
    }

    override fun changeBoard(board: BoardModel): Single<Boolean> {
        return remoteRepo.changeBoard(board, user.login, user.password)
    }

    override fun deleteTask(taskId: Int): Single<Boolean> {
        return remoteRepo.deleteTask(taskId, user.login, user.password)
    }

    override fun deleteBoard(boardId: Int): Single<Boolean> {
        return remoteRepo.deleteBoard(boardId, user.login, user.password)
    }

    override fun deleteUser(): Single<Boolean> {
        return remoteRepo.deleteUser(user.login, user.password)
    }
}
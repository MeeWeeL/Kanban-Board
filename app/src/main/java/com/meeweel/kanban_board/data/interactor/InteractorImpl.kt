package com.meeweel.kanban_board.data.interactor

import com.meeweel.kanban_board.data.network.boards.RemoteRepository
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class InteractorImpl(
    private val remoteRepo: RemoteRepository,
    private val localRepo: LocalUserRepository
) : Interactor {

    private val user by lazy {
        localRepo.getUser()[0]
    }

    override fun getUserLogin(): Single<String> {
        return Single.just(localRepo.getUser()[0].login)
    }

    override fun getBoards(): Single<List<BoardModel>> {
        return remoteRepo.getBoards(user.login, user.password)
    }

    override fun getBoardById(boardId: Int): Single<BoardModel> {
        return remoteRepo.getBoardById(boardId, user.login, user.password)
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

    override fun createBoardKey(boardId: Int): Single<String> {
        return remoteRepo.createBoardKey(boardId, user.login, user.password)
    }

    override fun addBoardByKey(boardKey: String): Single<Boolean> {
        return remoteRepo.addBoardByKey(boardKey, user.login, user.password)
    }

    override fun logOut(): Completable {
        return localRepo.logOut()
    }
}
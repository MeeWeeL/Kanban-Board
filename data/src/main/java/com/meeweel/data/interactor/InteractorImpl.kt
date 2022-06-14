package com.meeweel.data.interactor

import com.meeweel.kanban_board.data.network.boards.RemoteRepository
import com.meeweel.kanban_board.data.room.LocalUserRepository
import com.meeweel.core.basemodels.BoardModel
import com.meeweel.core.basemodels.TaskModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class InteractorImpl(
    private val remoteRepo: RemoteRepository,
    private val localRepo: LocalUserRepository
) : Interactor {

    private val user by lazy {
        localRepo.getUser()[0]
    }

    override fun getUserLogin(): Single<String> = Single.just(localRepo.getUser()[0].login)

    override fun getBoards(): Single<List<BoardModel>> =
        remoteRepo.getBoards(user.login, user.password)

    override fun getBoardById(boardId: Int): Single<BoardModel> =
        remoteRepo.getBoardById(boardId, user.login, user.password)

    override fun addTask(boardId: Int, task: TaskModel): Single<Boolean> =
        remoteRepo.addTask(boardId, task, user.login, user.password)

    override fun addBoard(boardTitle: String): Single<Boolean> =
        remoteRepo.addBoard(boardTitle, user.login, user.password)

    override fun changeTask(task: TaskModel): Single<Boolean> =
        remoteRepo.changeTask(task, user.login, user.password)

    override fun changeBoard(board: BoardModel): Single<Boolean> =
        remoteRepo.changeBoard(board, user.login, user.password)

    override fun deleteTask(taskId: Int): Single<Boolean> =
        remoteRepo.deleteTask(taskId, user.login, user.password)

    override fun deleteBoard(boardId: Int): Single<Boolean> =
        remoteRepo.deleteBoard(boardId, user.login, user.password)

    override fun deleteUser(): Single<Boolean> = remoteRepo.deleteUser(user.login, user.password)

    override fun createBoardKey(boardId: Int): Single<String> =
        remoteRepo.createBoardKey(boardId, user.login, user.password)

    override fun addBoardByKey(boardKey: String): Single<Boolean> =
        remoteRepo.addBoardByKey(boardKey, user.login, user.password)

    override fun logOut(): Completable = localRepo.logOut()
}
package com.meeweel.kanban_board.ui.screens.boardscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.data.interactor.Interactor
import com.meeweel.core.basemodels.Status
import com.meeweel.core.basemodels.TaskModel
import com.meeweel.core.basemodels.getTaskListByStatus
import com.meeweel.core.basemodels.states.BoardState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TasksScreenFragmentViewModel(private val interactor: Interactor)
    : ViewModel() {

    var boardId: Int? = null

    private val toDoLiveData: MutableLiveData<BoardState> = MutableLiveData()
    private val inProgressLiveData: MutableLiveData<BoardState> = MutableLiveData()
    private val doneLiveData: MutableLiveData<BoardState> = MutableLiveData()

    fun getToDoData(): LiveData<BoardState> = toDoLiveData
    fun getInProgressData(): LiveData<BoardState> = inProgressLiveData
    fun getDoneData(): LiveData<BoardState> = doneLiveData

    fun synchronizeData() = getDataFromInterceptor()

    fun createTask(task: TaskModel) = createNewTask(task)
    fun updateTask(task: TaskModel) = editTask(task)
    fun removeTask(taskId: Int) = deleteTask(taskId)

    private fun getDataFromInterceptor() {
        boardId?.let {
            interactor.getBoardById(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    toDoLiveData.postValue(BoardState.Loading)
                    inProgressLiveData.postValue(BoardState.Loading)
                    doneLiveData.postValue(BoardState.Loading)
                }
                .subscribe({ board ->
                    toDoLiveData.postValue(BoardState.Success(board.getTaskListByStatus(Status.TO_DO)))
                    inProgressLiveData.postValue(BoardState.Success(board.getTaskListByStatus(Status.IN_PROGRESS)))
                    doneLiveData.postValue(BoardState.Success(board.getTaskListByStatus(Status.DONE)))
                }, {
                    toDoLiveData.postValue(BoardState.Error(Throwable("Connection error")))
                    inProgressLiveData.postValue(BoardState.Error(Throwable("Connection error")))
                    doneLiveData.postValue(BoardState.Error(Throwable("Connection error")))
                })
        }
    }

    private fun editTask(task: TaskModel) = interactor.changeTask(task).sync()

    private fun createNewTask(task: TaskModel) = interactor.addTask(boardId!!, task).sync()

    private fun deleteTask(taskId: Int) = interactor.deleteTask(taskId).sync()

    private fun Single<Boolean>.sync() {
        this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getDataFromInterceptor()
            },{})
    }
}
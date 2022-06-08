package com.meeweel.kanban_board.ui.screens.boardscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.data.interactor.Interactor
import com.meeweel.kanban_board.data.interactor.InteractorImpl
import com.meeweel.kanban_board.domain.basemodels.Status
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.getTaskListByStatus
import com.meeweel.kanban_board.domain.basemodels.states.BoardState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BoardScreenFragmentViewModel(
    private val interactor: Interactor = InteractorImpl()) : ViewModel() {

    var boardId: Int? = null

    private val toDoLiveData: MutableLiveData<BoardState> =
        MutableLiveData()
    private val inProgressLiveData: MutableLiveData<BoardState> =
        MutableLiveData()
    private val doneLiveData: MutableLiveData<BoardState> =
        MutableLiveData()

    fun getToDoData(): LiveData<BoardState> {
        return toDoLiveData
    }

    fun getInProgressData(): LiveData<BoardState> {
        return inProgressLiveData
    }

    fun getDoneData(): LiveData<BoardState> {
        return doneLiveData
    }

    fun synchronizeData() = getDataFromInterceptor()

    fun createTask(task: TaskModel) = createNewTask(task)

    private fun createNewTask(task: TaskModel) {
        interactor.addTask(boardId!!, task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getDataFromInterceptor()
            },{})
    }

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
                .subscribe({
                    toDoLiveData.postValue(BoardState.Success(it.getTaskListByStatus(Status.TO_DO)))
                    inProgressLiveData.postValue(BoardState.Success(it.getTaskListByStatus(Status.IN_PROGRESS)))
                    doneLiveData.postValue(BoardState.Success(it.getTaskListByStatus(Status.DONE)))
                }, {
                    toDoLiveData.postValue(BoardState.Error(Throwable("Connection error")))
                    inProgressLiveData.postValue(BoardState.Error(Throwable("Connection error")))
                    doneLiveData.postValue(BoardState.Error(Throwable("Connection error")))
                })
        }
    }
}
package com.meeweel.kanban_board.ui.screens.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.data.interactor.Interactor
import com.meeweel.kanban_board.data.interactor.InteractorImpl
import com.meeweel.kanban_board.domain.basemodels.BoardModel
import com.meeweel.kanban_board.domain.basemodels.TaskModel
import com.meeweel.kanban_board.domain.basemodels.states.BoardsAppState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainFragmentViewModel(private val interactor: Interactor = InteractorImpl()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<BoardsAppState> = MutableLiveData()  // Создаем ячейку памяти для хранения

    fun getData(): LiveData<BoardsAppState> {  // Отдаёт наружу лайвдату, через которую можно следить за изменениями данных
        return liveDataToObserve
    }

    fun getBoards() = getDataFromInterceptor() // Обновить данные во вьюмоделе


    fun updateBoard(board: BoardModel) = changeBoardTitle(board)
    fun createBoard(boardName: String) = createNewBoard(boardName)
    fun removeBoard(boardId: Int) = deleteBoard(boardId)

    private fun getDataFromInterceptor() {
        interactor.getBoards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataToObserve.postValue(BoardsAppState.Loading)
            }
            .subscribe({
                liveDataToObserve.postValue(BoardsAppState.Success(it))
            }, {
                liveDataToObserve.postValue(BoardsAppState.Error(Throwable("Connection error")))
            })
    }

    private fun createNewBoard(boardName: String) {
        interactor.addBoard(boardName).sync()
    }

    private fun deleteBoard(boardId: Int) {
        interactor.deleteTask(boardId).sync()
    }

    private fun changeBoardTitle(board: BoardModel) {
        interactor.changeBoard(board).sync()
    }

    private fun Single<Boolean>.sync() {
        this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getDataFromInterceptor()
            },{})
    }
}
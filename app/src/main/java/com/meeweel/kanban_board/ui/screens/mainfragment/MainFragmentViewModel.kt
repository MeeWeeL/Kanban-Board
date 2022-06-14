package com.meeweel.kanban_board.ui.screens.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.data.interactor.Interactor
import com.meeweel.core.basemodels.BoardModel
import com.meeweel.core.basemodels.states.BoardsAppState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainFragmentViewModel(private val interactor: Interactor) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<BoardsAppState> = MutableLiveData()
    private val liveDataBoardKey: MutableLiveData<String> = MutableLiveData()

    fun getData(): LiveData<BoardsAppState> = liveDataToObserve
    fun getKeyData(): LiveData<String> = liveDataBoardKey

    fun getBoards() = getDataFromInterceptor()

    fun updateBoard(board: BoardModel) = changeBoardTitle(board)
    fun createBoard(boardName: String) = createNewBoard(boardName)
    fun removeBoard(boardId: Int) = deleteBoard(boardId)

    fun createBoardKey(boardId: Int) = createABoardKey(boardId)
    fun addBoardById(boardKey: String) = addABoardById(boardKey)

    private fun createABoardKey(boardId: Int) {
        interactor.createBoardKey(boardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataBoardKey.postValue(it)
            },{})
    }

    private fun addABoardById(boardKey: String) = interactor.addBoardByKey(boardKey).sync()

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

    private fun createNewBoard(boardName: String) = interactor.addBoard(boardName).sync()

    private fun deleteBoard(boardId: Int) = interactor.deleteBoard(boardId).sync()

    private fun changeBoardTitle(board: BoardModel) = interactor.changeBoard(board).sync()

    private fun Single<Boolean>.sync() {
        this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getDataFromInterceptor()
            },{})
    }
}
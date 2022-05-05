package com.meeweel.kanban_board.ui.screens.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.data.interactor.Interactor
import com.meeweel.kanban_board.data.interactor.InteractorImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainFragmentViewModel(private val interactor: Interactor = InteractorImpl()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()  // Создаем ячейку памяти для хранения

    fun getData(): LiveData<AppState> {  // Отдаёт наружу лайвдату, через которую можно следить за изменениями данных
        return liveDataToObserve
    }

    fun getBoards() = getDataFromInterceptor() // Обновить данные во вьюмоделе

    private fun getDataFromInterceptor() {
        interactor.getBoards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataToObserve.postValue(AppState.Loading)
            }
            .subscribe({
                liveDataToObserve.postValue(AppState.Success(it))
            }, {
                liveDataToObserve.postValue(AppState.Error(Throwable("Connection error")))
            })
    }
}
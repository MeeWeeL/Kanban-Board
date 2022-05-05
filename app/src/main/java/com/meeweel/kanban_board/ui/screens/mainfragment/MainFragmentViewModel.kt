package com.meeweel.kanban_board.ui.screens.mainfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.domain.basemodels.FakeRepository

class MainFragmentViewModel(private val repo: FakeRepository = FakeRepository()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()  // Создаем ячейку памяти для хранения

    fun getData(): LiveData<AppState> {  // Отдаёт наружу лайвдату, через которую можно следить за изменениями данных
        return liveDataToObserve
    }

    fun getLocalData() = getDataFromLocalSource() // Обновить данные во вьюмоделе

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(100)
            liveDataToObserve.postValue(
                AppState.Success(
                    repo.getBoards().sortedBy { it.id }
                )
            )
        }.start()
    }
}
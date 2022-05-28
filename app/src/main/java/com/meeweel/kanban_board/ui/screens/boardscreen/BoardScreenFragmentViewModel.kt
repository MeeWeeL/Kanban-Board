package com.meeweel.kanban_board.ui.screens.boardscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.domain.basemodels.states.TasksAppState
import com.meeweel.kanban_board.ui.screens.boardscreen.tasklists.BaseBoardScreenFragment.Companion.board

class BoardScreenFragmentViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<TasksAppState> =
        MutableLiveData()  // Создаем ячейку памяти для хранения

    fun getData(): LiveData<TasksAppState> {  // Отдаёт наружу лайвдату, через которую можно следить за изменениями данных
        return liveDataToObserve
    }

    init {
        liveDataToObserve.postValue(TasksAppState.Success(board!!.toDoList))
    }

//    fun getTasks() = getDataFromInterceptor() // Обновить данные во вьюмоделе

//    private fun getDataFromBoard() {
//        liveDataToObserve.postValue(TasksAppState.Success(it))
//    }
}
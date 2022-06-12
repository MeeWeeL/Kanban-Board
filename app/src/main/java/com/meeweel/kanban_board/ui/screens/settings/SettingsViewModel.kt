package com.meeweel.kanban_board.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.data.interactor.Interactor
import com.meeweel.kanban_board.domain.basemodels.states.SettingsState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SettingsViewModel(private val interactor: Interactor) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<SettingsState> = MutableLiveData()

    fun getData(): LiveData<SettingsState> = liveDataToObserve

    fun logOut() = logOutFromAccount()
    fun deleteAccount() = deleteUserAccount()
    fun getUserName() = getLogin()

    private fun getLogin() {
        interactor.getUserLogin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataToObserve.postValue(SettingsState.Loading)
            }
            .subscribe({
                liveDataToObserve.postValue(SettingsState.UserName(it))
            }, {
                liveDataToObserve.postValue(SettingsState.Error(Throwable(THROWABLE)))
            })
    }

    private fun logOutFromAccount() {
        interactor.logOut().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataToObserve.postValue(SettingsState.Loading)
            }
            .subscribe({
                liveDataToObserve.postValue(SettingsState.LoggedOut)
            }, {
                liveDataToObserve.postValue(SettingsState.Error(Throwable(THROWABLE)))
            })
    }

    private fun deleteUserAccount() {
        interactor.deleteUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                liveDataToObserve.postValue(SettingsState.Loading)
            }
            .subscribe({
                liveDataToObserve.postValue(SettingsState.AccountDeleted)
           }, {
                liveDataToObserve.postValue(SettingsState.Error(Throwable(THROWABLE)))
            })
    }

    companion object {
        const val THROWABLE = "Connection error"
    }
}
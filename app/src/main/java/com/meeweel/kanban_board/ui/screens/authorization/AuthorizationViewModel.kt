package com.meeweel.kanban_board.ui.screens.authorization

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meeweel.kanban_board.app.App.AppInstance.context
import com.meeweel.kanban_board.data.interactor.AuthorizationInteractor
import com.meeweel.kanban_board.data.interactor.AuthorizationInteractorImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthorizationViewModel(private val interactor: AuthorizationInteractor = AuthorizationInteractorImpl()) :
    ViewModel() {

    private val liveIsAuth: MutableLiveData<Boolean> = MutableLiveData()
    private val liveDataToObserve: MutableLiveData<AuthorizationState> = MutableLiveData()

    fun isAuth(): LiveData<Boolean> {
        return liveIsAuth
    }
    fun getData(): LiveData<AuthorizationState> {
        return liveDataToObserve
    }

    fun signIn(login: String, password: String) {
        interactor.signIn(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveDataToObserve.postValue(AuthorizationState.Loading) }
            .subscribe({
                if (it) interactor.saveAuthorization(login, password)
                liveIsAuth.postValue(it)
            }, {

            })
    }

    fun signUp(login: String, password: String) {
        interactor.signUp(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveDataToObserve.postValue(AuthorizationState.Loading) }
            .subscribe({
                if (it) {
                    interactor.saveAuthorization(login, password)
                    checkAuthorization()
                } else {
                    liveDataToObserve.postValue(AuthorizationState.Done)
                }
            }, {

            })
    }

    fun checkAuthorization() {
        val data = interactor.checkAuthorization()
        if (data.isNotEmpty()) {
            signIn(data[0].login, data[0].password)
            Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show()
//                liveDataToObserve.postValue(AuthorizationState.Success(data[0]))
//                liveDataToObserve.postValue(AuthorizationState.Success(data[0]))
//                signIn(data[0].login, data[0].password)
        }
        liveDataToObserve.postValue(AuthorizationState.Done)
    }
}
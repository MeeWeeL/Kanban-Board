package com.meeweel.core.basemodels.states

sealed class SettingsState {
    data class UserName(val userName: String) : SettingsState()
    class Error(val error: Throwable) : SettingsState()
    object LoggedOut : SettingsState()
    object AccountDeleted : SettingsState()
    object Loading : SettingsState()
}
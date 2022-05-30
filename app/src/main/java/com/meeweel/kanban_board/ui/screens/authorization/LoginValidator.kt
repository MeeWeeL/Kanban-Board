package com.meeweel.kanban_board.ui.screens.authorization

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

class LoginValidator : TextWatcher {
    internal var isValidLogin = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(editableText: Editable?) {
        isValidLogin = isValidLogin(editableText)
    }

    companion object {
        private val PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+\$).{20,}\$"
        )

        fun isValidLogin(password: CharSequence?): Boolean {
            return password != null && PASSWORD_PATTERN.matcher(password).matches()
        }
    }
}
package com.meeweel.kanban_board.ui.screens.authorization

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

class PasswordValidator : TextWatcher {
    internal var isValidPassword = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(editableText: Editable?) {
        isValidPassword = isValidPassword(editableText)
    }

    companion object {
        private val PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=\\S+).{12,}\$"
        )

        fun isValidPassword(password: CharSequence?): Boolean {
            return password != null && PASSWORD_PATTERN.matcher(password).matches()
        }
    }
}
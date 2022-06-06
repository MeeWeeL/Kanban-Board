package com.meeweel.kanban_board.ui.screens.registration

import android.text.Editable
import android.text.TextWatcher
import com.meeweel.kanban_board.R
import java.util.regex.Pattern

class ConfirmPasswordValidator : TextWatcher {
    internal var isConfirmPassword = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    override fun afterTextChanged(editableText: Editable?) {
        isConfirmPassword = isConfirmPassword(editableText)
    }

    companion object {
        private const val result = R.id.editTextPasswordRegistration.toString()

        private val CONFIRM_PASSWORD_PATTERN = Pattern.compile(
            result
        )
    }

    private fun isConfirmPassword(confirmPassword: CharSequence?): Boolean {
        return confirmPassword != null && CONFIRM_PASSWORD_PATTERN.matcher(confirmPassword)
            .matches()
    }
}
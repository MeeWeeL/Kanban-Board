package com.meeweel.kanban_board.domain.basemodels

enum class Status(val int: Int) {
    TO_DO(0),
    IN_PROGRESS(1),
    DONE(2)
}
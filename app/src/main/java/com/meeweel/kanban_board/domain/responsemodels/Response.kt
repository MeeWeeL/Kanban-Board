package com.meeweel.kanban_board.domain.responsemodels

data class Response(
    val board_id: Int,
    var board_title: String,
    val board_host: String,
    val board_key: String,
    val tasks: List<TaskResponse> = listOf()
)
package com.meeweel.kanban_board.domain.responsemodels

import com.meeweel.kanban_board.domain.basemodels.BoardModel

data class Response(
    val board_id: Int,
    var board_title: String,
    val board_host: String,
    val board_key: String,
    val tasks: List<TaskResponse> = listOf()
)

fun Response.toBoardModel() = BoardModel(
    id = this.board_id,
    name = this.board_title,
    host = this.board_host,
    key = this.board_key,
    toDoList = this.tasks.toTaskModelList().toMutableList()
)

fun List<Response>.toBoardModelList() = this.map { it.toBoardModel() }
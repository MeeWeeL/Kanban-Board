package com.meeweel.kanban_board.domain.basemodels

data class BoardModel(
    val id: Int,
    var name: String,
    val host: String,
    val toDoList: MutableList<TaskModel> = mutableListOf()
)

fun BoardModel.getTaskListByStatus(status: Status) : List<TaskModel> {
    val newList: MutableList<TaskModel> = mutableListOf()
    for (item in this.toDoList)
        if (item.status == status) newList.add(item)
    return newList
}
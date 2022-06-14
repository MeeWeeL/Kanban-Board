package com.meeweel.core.basemodels

data class TaskModel(
    val id: Int,
    var name: String = "Task",
    var description: String = "",
    var status: Status = Status.TO_DO,
    var priority: Priority = Priority.NONE,
    var performer: String = "None"
)
package com.meeweel.kanban_board.data

import androidx.room.PrimaryKey

data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String = "Task",
    var description: String = "",
    var status: Status = Status.TO_DO,
    var priority: Priority = Priority.NONE,
    var performer: String = "None"
)
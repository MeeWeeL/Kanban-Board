package com.meeweel.kanban_board.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val login: String,
    val password: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)
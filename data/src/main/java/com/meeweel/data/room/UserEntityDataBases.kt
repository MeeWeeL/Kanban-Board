package com.meeweel.kanban_board.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserEntityDataBases : RoomDatabase() {

    abstract fun entityDao(): UserEntityDao
}
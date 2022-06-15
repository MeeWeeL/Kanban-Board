package com.meeweel.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.meeweel.data.room.UserEntity
import com.meeweel.data.room.UserEntityDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserEntityDataBases : RoomDatabase() {

    abstract fun entityDao(): UserEntityDao
}
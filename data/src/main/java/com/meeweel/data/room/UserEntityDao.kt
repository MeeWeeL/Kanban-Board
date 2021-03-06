package com.meeweel.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meeweel.data.room.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserEntityDao {

    @Query("SELECT * FROM UserEntity LIMIT 1")
    fun getUserData() : Single<List<UserEntity>>

    @Query("SELECT * FROM UserEntity")
    fun getUser() : List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserData(entity: UserEntity)

    @Query("DELETE FROM UserEntity WHERE id = 1")
    fun logOut(): Completable
}
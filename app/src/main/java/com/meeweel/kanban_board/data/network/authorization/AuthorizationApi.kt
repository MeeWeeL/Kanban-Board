package com.meeweel.kanban_board.data.network.authorization

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorizationApi {

    @GET("api.php?q=sign_in")
    fun signIn(
        @Query("my_login") login: String,
        @Query("my_password") password: String
    ): Single<Boolean>

    @GET("api.php?q=sign_up")
    fun signUp(
        @Query("my_login") login: String,
        @Query("my_password") password: String
    ): Single<Boolean>
}
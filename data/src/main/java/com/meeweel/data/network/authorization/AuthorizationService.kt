package com.meeweel.data.network.authorization

import com.meeweel.kanban_board.data.network.BaseInterceptor
import com.meeweel.kanban_board.data.network.BaseRetrofitService

class AuthorizationService : BaseRetrofitService() {

    fun getService(): AuthorizationApi =
        createRetrofit(BaseInterceptor.interceptor).create(AuthorizationApi::class.java)
}
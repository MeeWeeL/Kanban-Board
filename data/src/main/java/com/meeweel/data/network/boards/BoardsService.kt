package com.meeweel.data.network.boards

import com.meeweel.data.network.BaseInterceptor
import com.meeweel.data.network.BaseRetrofitService

class BoardsService : BaseRetrofitService() {

    fun getService(): KanbanApi =
        createRetrofit(BaseInterceptor.interceptor).create(KanbanApi::class.java)
}
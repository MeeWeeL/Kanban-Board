package com.meeweel.kanban_board.data.network.boards

import com.meeweel.kanban_board.data.network.BaseInterceptor
import com.meeweel.kanban_board.data.network.BaseRetrofitService

class BoardsService : BaseRetrofitService() {

    fun getService(): KanbanApi =
        createRetrofit(BaseInterceptor.interceptor).create(KanbanApi::class.java)
}
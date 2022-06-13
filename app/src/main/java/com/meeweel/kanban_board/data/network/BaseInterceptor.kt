package com.meeweel.kanban_board.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseInterceptor private constructor() : Interceptor {

    private var responseCode: Int = 0

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code
        return response
    }

    companion object {

        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }
}
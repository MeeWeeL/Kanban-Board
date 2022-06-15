package com.meeweel.data.network.authorization

import com.meeweel.data.network.BaseInterceptor
import com.meeweel.data.network.BaseRetrofitService

class AuthorizationService : BaseRetrofitService() {

    fun getService(): AuthorizationApi =
        createRetrofit(BaseInterceptor.interceptor).create(AuthorizationApi::class.java)
}
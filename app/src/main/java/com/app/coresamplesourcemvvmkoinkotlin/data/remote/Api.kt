package com.app.coresamplesourcemvvmkoinkotlin.data.remote

import com.app.coresamplesourcemvvmkoinkotlin.data.pojos.User
import io.reactivex.Single
import retrofit2.http.*


interface Api {

    @GET("api/v1/auth/me")
    fun doGetProfile(
        @Header("Authorization") token: String
    ): Single<BaseApiResponse<User>>

}
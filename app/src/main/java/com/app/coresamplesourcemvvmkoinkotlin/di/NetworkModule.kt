package com.app.coresamplesourcemvvmkoinkotlin.di

import com.app.coresamplesourcemvvmkoinkotlin.data.remote.Api
import com.app.coresamplesourcemvvmkoinkotlin.data.remote.MainRepo
import com.app.coresamplesourcemvvmkoinkotlin.utils.Common
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


private val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
    HttpLoggingInterceptor.Level.BODY)

private val interceptor: Interceptor = Interceptor {
        chain ->
    var countRetry = 0
    var response : Response? = null
    var request = chain.request()
    var ioException : IOException? = null

    while (countRetry < 3 && (response == null || response.isSuccessful.not())){
        try{
            val newRequest = request.newBuilder()
            newRequest.headers(request.headers())
            newRequest.addHeader("Content-Type", "application/json")
            newRequest.method(request.method(), request.body())
            response = chain.proceed(newRequest.build())
        }catch (iox : IOException){
            ioException = iox
        }finally {
            countRetry = countRetry.inc()
        }
    }

    if (ioException != null){

        throw ioException
    }

    response!!
}

val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(45, TimeUnit.SECONDS)
    .writeTimeout(45, TimeUnit.SECONDS)
    .addInterceptor(httpLoggingInterceptor)

val okHttpClient: OkHttpClient = okHttpBuilder.build()

val retrofit: Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(Common.URL_DOMAIN)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val provideApi : Api = retrofit.create(Api::class.java)

val networkModule = module {
    single { MainRepo(get(), get(),provideApi) }
}
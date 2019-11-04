package com.app.coresamplesourcemvvmkoinkotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import org.json.JSONObject
import retrofit2.HttpException

object AppConstants{


    val ERROR_CONNECT_SERVER_FAIL = "Lỗi kết nối server!"
    val PREFERENCE_NAME = "PREFERENCE_DB"
    val ROOM_NAME = "ROOM_DB"

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return false
    }
    fun getMessageErrorResponse(throwable: Throwable): String {
        Log.d("Rio throwable: --> ", "${throwable.message} ")
        var mess: String
        try {
            mess = JSONObject((throwable as HttpException).response().errorBody()?.string()).getString("error")
        } catch (e: java.lang.Exception) {
            mess = e.message.toString()
        }
        return if (mess.isNotEmpty()) {
            mess
        } else {
            ERROR_CONNECT_SERVER_FAIL
        }
    }

}
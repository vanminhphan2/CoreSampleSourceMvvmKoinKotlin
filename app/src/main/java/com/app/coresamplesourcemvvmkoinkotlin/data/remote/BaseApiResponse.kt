package com.app.coresamplesourcemvvmkoinkotlin.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseApiResponse<RemoteType> {

    @SerializedName("status")
    @Expose
    var status = false

    @SerializedName("code")
    @Expose
    var code = 0

    @SerializedName("data")
    @Expose
    var data: RemoteType? = null

    @SerializedName("error")
    @Expose
    var error: String? = null
}

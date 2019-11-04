package com.app.coresamplesourcemvvmkoinkotlin.data.remote

import com.app.coresamplesourcemvvmkoinkotlin.data.local.prefs.PreferencesHelper
import com.app.coresamplesourcemvvmkoinkotlin.data.local.room.Database
import com.app.coresamplesourcemvvmkoinkotlin.data.pojos.User
import io.reactivex.Single
import okhttp3.MultipartBody


class MainRepo constructor(private val prefs: PreferencesHelper, private val db: Database, private val api: Api) {

//------------------------ApiHelper------------------------//


    private fun transformToken(token: String): String {
        return "Bearer $token"
    }

    private fun acceptFieldHeader(): String {
        return "application/json"
    }

    fun doGetProfile(token:String): Single<Resource<User>> {
        return Single.create { emitter ->
            api.doGetProfile(transformToken(token))
                .subscribe({ res ->
                    if (res != null) {
                        if (res.status)
                            emitter.onSuccess(Resource.success(res.data))
                        else emitter.onSuccess(Resource.fail(res.error!!, res.code))
                    }
                }, { onError ->
                    emitter.onError(onError)
                })
        }
    }

    //    //------------------------Preferences------------------------//

    fun getLoginToken(): String? {
        return prefs.getLoginToken()
    }

    fun setLoginToken(token: String) {
        prefs.setLoginToken(token)
    }

    fun getLoginUserId(): Int {
        return prefs.getUserId()
    }

    fun setLoginUserId(id: Int) {
        prefs.setUserId(id)
    }


//    //------------------------DbHelper------------------------//


    fun setUserLogin(user: User): Single<Resource<Long>> {
        return Single.fromCallable { db.userDao().insert(user) }
            .map { value -> Resource.success(value) }

    }

    fun getUserLogin(): Single<Resource<User>> {
        return Single.fromCallable { db.userDao().findUserById(getLoginUserId()) }
            .map { value -> Resource.success(value) }
    }

}
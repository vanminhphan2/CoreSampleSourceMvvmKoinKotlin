package com.app.coresamplesourcemvvmkoinkotlin.feature.main

import com.app.coresamplesourcemvvmkoinkotlin.base.BaseViewModel
import com.app.coresamplesourcemvvmkoinkotlin.data.remote.MainRepo
import com.app.coresamplesourcemvvmkoinkotlin.utils.AppConstants
import com.app.coresamplesourcemvvmkoinkotlin.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(val mainRepo: MainRepo) : BaseViewModel() {


    val onSetUpInfoUser: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun getInfo() {
        disposable.add(
            mainRepo.doGetProfile(mainRepo.getLoginToken()!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    logD("getInfo 111 ", res.data.toString())
                    onSetUpInfoUser.value = res?.data != null
                },
                    { t ->
                        messageError = AppConstants.getMessageErrorResponse(t)
                        logD(messageError)
                        onShowMessageEvent().value = messageError
                        hideLoading()
                    })
        )

    }
}
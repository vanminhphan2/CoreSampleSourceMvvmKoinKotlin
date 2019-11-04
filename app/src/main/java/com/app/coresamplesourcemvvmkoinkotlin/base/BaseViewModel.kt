package com.app.coresamplesourcemvvmkoinkotlin.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.coresamplesourcemvvmkoinkotlin.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel : ViewModel() {

    protected var disposable: CompositeDisposable = CompositeDisposable()
    protected var loadingIndicator: MutableLiveData<Boolean> = MutableLiveData()
    private var onLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private var onLoadingHeart: SingleLiveEvent<Boolean> = SingleLiveEvent()
    protected var onShowMessage: SingleLiveEvent<String> = SingleLiveEvent()
    protected var messageError:String=""

    override fun onCleared() {
        super.onCleared()

        Log.d("Rio", "onCleared")
        disposeAll()
        System.gc()
    }

    private fun disposeAll() {
        if (disposable.size() > 0) {
            disposable.clear()
        }
    }

    fun onShowMessageEvent(): SingleLiveEvent<String> {
        return onShowMessage
    }

    fun onLoadingEvent(): SingleLiveEvent<Boolean> {
        return onLoading
    }

    fun onLoadingHeartEvent(): SingleLiveEvent<Boolean> {
        return onLoadingHeart
    }

    fun showLoading() {
        onLoadingEvent().value = true
    }

    fun hideLoading() {
        onLoadingEvent().value = false
    }

    fun showLoadingHeart() {
        onLoadingHeartEvent().value = true
    }

    fun hideLoadingHeart() {
        onLoadingHeartEvent().value = false
    }

    fun logD(content: String) {
        Log.d("Rio content log: --> ", content)
    }

    fun logD(content1: String, content2: String) {
        Log.d("Rio content log: --> ", "$content1 :  $content2")
    }

    fun logError(content: String) {
        Log.e("Rio error log: --> ", content)
    }

    fun logError(content1: String, content2: String) {
        Log.e("Rio error log: --> ", "$content1 :  $content2")
    }
}
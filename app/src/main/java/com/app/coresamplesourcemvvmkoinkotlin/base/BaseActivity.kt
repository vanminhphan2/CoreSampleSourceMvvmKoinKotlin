package com.app.coresamplesourcemvvmkoinkotlin.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.coresamplesourcemvvmkoinkotlin.R
import com.app.coresamplesourcemvvmkoinkotlin.utils.AppConstants


abstract class BaseActivity : AppCompatActivity() {


    protected abstract fun initializeViews()
    protected abstract fun registerEvents()
    protected abstract fun initData()
    protected var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        loadingDialog = Dialog(this)
        loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog!!.setContentView(R.layout.dialog_loading)
        loadingDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loadingDialog!!.setCancelable(false)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.blackColor)
        }
        return super.onCreateView(name, context, attrs)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(loadingDialog!=null && loadingDialog!!.isShowing)
            loadingDialog!!.dismiss()
    }

    fun isNetworkConnected(): Boolean {
        return AppConstants.isNetworkConnected(applicationContext)
    }

    fun showLoading() {
        if (loadingDialog == null) {

            loadingDialog = Dialog(this)
            loadingDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingDialog!!.setContentView(R.layout.dialog_loading)
            loadingDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            loadingDialog!!.setCancelable(false)
        }
        if (!loadingDialog!!.isShowing)
            loadingDialog!!.show()
    }

    fun hideLoading() {
        if (loadingDialog != null)
            loadingDialog!!.dismiss()
    }


    fun showInternetDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error!!!")
        builder.setMessage("Please check your internet connection and try again!")
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }
        builder.show()
    }


    fun logD(content: String) {
        Log.d("Rio content log: --> ", content)
    }

    fun logD(content1: String, content2: String) {
        Log.d("Rio content log: --> ", "$content1 :  $content2")
    }
}
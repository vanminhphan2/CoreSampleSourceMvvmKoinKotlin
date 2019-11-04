package com.app.coresamplesourcemvvmkoinkotlin.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.app.coresamplesourcemvvmkoinkotlin.R
import com.app.coresamplesourcemvvmkoinkotlin.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :  BaseActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        registerEvents()
        initData()
    }


    override fun initializeViews() {

    }

    override fun registerEvents() {


        viewModel.onSetUpInfoUser.observe(this, Observer {
         //do something

        })
    }

    override fun initData() {

        viewModel.getInfo()
    }

}

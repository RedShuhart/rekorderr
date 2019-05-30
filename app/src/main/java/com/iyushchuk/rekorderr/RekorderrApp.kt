package com.iyushchuk.rekorderr

import androidx.multidex.MultiDexApplication
import com.iyushchuk.rekorderr.core.di.AppComponent
import com.iyushchuk.rekorderr.core.di.DaggerAppComponent
import com.iyushchuk.rekorderr.core.di.util.AppContextModule


class RekorderrApp : MultiDexApplication() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().appContextModule(AppContextModule(this)).build()
    }
}
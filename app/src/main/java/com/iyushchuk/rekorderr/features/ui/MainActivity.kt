package com.iyushchuk.rekorderr.features.ui

import android.os.Bundle
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.features.common.BaseActivity
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import javax.inject.Inject

class MainActivity: BaseActivity() {

    @Inject
    lateinit var router: AppRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getActivityComponent().inject(this)
        setContentView(R.layout.base_activity)
        if (savedInstanceState == null) {
            router.openSplashScreen()
        }
    }

    override fun onBackPressed() {
        val currentMvpFragment = getCurrentFragment()
        if (currentMvpFragment.onBackPressed()) {
            return
        }

        super.onBackPressed()
    }

    private fun getCurrentFragment() =
        supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseMvpFragment

}
package com.iyushchuk.rekorderr.core.di.ui

import com.iyushchuk.rekorderr.core.di.ui.fragments.SplashComponent
import com.iyushchuk.rekorderr.core.di.ui.fragments.SplashModule
import com.iyushchuk.rekorderr.features.common.BaseActivity
import com.iyushchuk.rekorderr.features.ui.MainActivity
import dagger.Subcomponent

@UIScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    operator fun plus(module: SplashModule): SplashComponent

    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)
}
package com.iyushchuk.rekorderr.core.di.ui

import com.iyushchuk.rekorderr.core.di.ui.fragments.*
import com.iyushchuk.rekorderr.features.common.BaseActivity
import com.iyushchuk.rekorderr.features.ui.MainActivity
import dagger.Subcomponent

@UIScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    operator fun plus(module: SplashModule): SplashComponent
    operator fun plus(module: FeedModule): FeedComponent
    operator fun plus(module: VideoRekorderModule): VideoRekorderComponent
    operator fun plus(module: PhotoRekorderModule): PhotoRekorderComponent
    operator fun plus(module: RekordFormModule): RekordFormComponent
    operator fun plus(module: PhotoViwerModule): PhotoViwerComponent

    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)
}
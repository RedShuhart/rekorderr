package com.iyushchuk.rekorderr.core.di.ui.fragments

import com.iyushchuk.rekorderr.features.ui.splash.SplashFragment
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(fragment: SplashFragment)
}
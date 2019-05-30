package com.iyushchuk.rekorderr.core.di.ui

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: FragmentActivity) {

    @UIScope
    @Provides
    fun provideActivity() = activity
}

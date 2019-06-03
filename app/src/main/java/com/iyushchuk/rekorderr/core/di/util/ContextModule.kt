package com.iyushchuk.rekorderr.core.di.util

import android.content.Context
import com.iyushchuk.rekorderr.core.di.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule(private val context: Context) {

    @Singleton
    @Provides
    internal fun provideAppContext(): Context {
        return context
    }
}

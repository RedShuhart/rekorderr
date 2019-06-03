package com.iyushchuk.rekorderr.core.di.util

import com.iyushchuk.rekorderr.core.di.AppScope
import com.iyushchuk.rekorderr.core.schedulers.AppRxSchedulers
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Singleton
    @Provides
    internal fun provideRxSchedulers(): RxSchedulers = AppRxSchedulers()
}

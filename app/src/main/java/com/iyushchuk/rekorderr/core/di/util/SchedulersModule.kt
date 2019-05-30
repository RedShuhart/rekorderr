package com.iyushchuk.rekorderr.core.di.util

import com.iyushchuk.rekorderr.core.di.AppScope
import com.iyushchuk.rekorderr.core.schedulers.AppRxSchedulers
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @AppScope
    @Provides
    internal fun provideRxSchedulers(): RxSchedulers = AppRxSchedulers()
}

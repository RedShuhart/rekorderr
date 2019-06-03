package com.iyushchuk.rekorderr.core.di

import com.iyushchuk.rekorderr.core.di.util.AppContextModule
import com.iyushchuk.rekorderr.core.di.util.RoutingModule
import com.iyushchuk.rekorderr.core.di.util.SchedulersModule
import com.iyushchuk.rekorderr.core.di.ui.ActivityComponent
import com.iyushchuk.rekorderr.core.di.ui.ActivityModule
import com.iyushchuk.rekorderr.core.di.util.DataBaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoutingModule::class, AppContextModule::class, SchedulersModule::class, DataBaseModule::class])
interface AppComponent {

    operator fun plus(activityModule: ActivityModule): ActivityComponent
}

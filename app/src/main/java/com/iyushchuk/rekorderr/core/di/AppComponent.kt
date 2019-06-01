package com.iyushchuk.rekorderr.core.di

import com.iyushchuk.rekorderr.core.di.db.DataBaseModule
import com.iyushchuk.rekorderr.core.di.db.RepositoryModule
import com.iyushchuk.rekorderr.core.di.util.AppContextModule
import com.iyushchuk.rekorderr.core.di.util.RoutingModule
import com.iyushchuk.rekorderr.core.di.util.SchedulersModule
import com.iyushchuk.rekorderr.core.di.ui.ActivityComponent
import com.iyushchuk.rekorderr.core.di.ui.ActivityModule
import dagger.Component

@AppScope
@Component(modules = [RoutingModule::class, AppContextModule::class, SchedulersModule::class, DataBaseModule::class, RepositoryModule::class])
interface AppComponent {

    operator fun plus(activityModule: ActivityModule): ActivityComponent
}

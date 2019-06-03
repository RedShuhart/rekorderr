package com.iyushchuk.rekorderr.core.di.util

import com.iyushchuk.rekorderr.core.di.AppScope
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Singleton

@Module
class RoutingModule {

    @Singleton
    @Provides
    fun provideCicerone(router: AppRouter): Cicerone<AppRouter> = Cicerone.create(router)

    @Singleton
    @Provides
    fun provideNavigationHolder(cicerone: Cicerone<AppRouter>): NavigatorHolder = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun provideRouter() = AppRouter()
}
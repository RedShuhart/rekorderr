package com.iyushchuk.rekorderr.core.navigation


import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import ru.terrakok.cicerone.Router

class AppRouter: Router() {
    fun openSplashScreen() {
        newRootScreen(AppScreens.SplashScreen())
    }

    fun openFeedCardsScreen(feed: MutableList<Rekord>) {
        newRootScreen(AppScreens.FeedScreen(feed))
    }
}
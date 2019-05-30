package com.iyushchuk.rekorderr.core.navigation


import ru.terrakok.cicerone.Router

class AppRouter: Router() {
    fun openSplashScreen() {
        newRootScreen(AppScreens.SplashScreen())
    }
}
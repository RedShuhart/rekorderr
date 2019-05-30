package com.iyushchuk.rekorderr.core.navigation

import androidx.fragment.app.Fragment
import com.iyushchuk.rekorderr.features.ui.splash.SplashFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AppScreens {

    class SplashScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SplashFragment()
        }
    }
}
package com.iyushchuk.rekorderr.core.navigation

import androidx.fragment.app.Fragment
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.features.ui.feed.FeedFragment
import com.iyushchuk.rekorderr.features.ui.photoviewer.PhotoViewerFragment
import com.iyushchuk.rekorderr.features.ui.splash.SplashFragment
import com.iyushchuk.rekorderr.features.ui.video.PhotoRekorderFragment
import com.iyushchuk.rekorderr.features.ui.video.RekordFormFragment
import com.iyushchuk.rekorderr.features.ui.video.VideoRekorderFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class AppScreens {

    class SplashScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SplashFragment()
        }
    }

    class VideoRekorderScreen(private val video: Rekord): SupportAppScreen() {
        override fun getFragment(): Fragment {
            return VideoRekorderFragment.newInstance(video)
        }
    }

    class PhotoRekorderScreen(private val photo: Rekord): SupportAppScreen() {
        override fun getFragment(): Fragment {
            return PhotoRekorderFragment.newInstance(photo)
        }
    }

    class FeedScreen(private val feed: MutableList<Rekord>): SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FeedFragment.newInstance(feed)
        }
    }

    class RekordFormScreen(private val rekord: Rekord): SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RekordFormFragment.newInstance(rekord)
        }
    }

    class PhotoViewerScreen(private val photo: Rekord): SupportAppScreen() {
        override fun getFragment(): Fragment {
            return PhotoViewerFragment.newInstance(photo)
        }
    }
}
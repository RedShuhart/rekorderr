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

    fun openVideoRekorderScreen(video: Rekord) {
        newRootScreen(AppScreens.VideoRekorderScreen(video))
    }

    fun openPhotoRekorderScreen(photo: Rekord) {
        newRootScreen(AppScreens.PhotoRekorderScreen(photo))
    }

    fun openRekordFormScreen(photo: Rekord) {
        newRootScreen(AppScreens.RekordFormScreen(photo))
    }

    fun openPhotoViewerScreen(photo: Rekord) {
        newRootScreen(AppScreens.PhotoViewerScreen(photo))
    }

    fun openRekordPlayerScreen(rekord: Rekord) {
        newRootScreen(AppScreens.RekordPlayerScreen(rekord))
    }

    fun openAudioRekorderScreen(audio: Rekord) {
        newRootScreen(AppScreens.AudioRekorderScreen(audio))
    }
}
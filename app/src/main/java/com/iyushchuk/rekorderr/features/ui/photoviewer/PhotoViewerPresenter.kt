package com.iyushchuk.rekorderr.features.ui.photoviewer

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import com.iyushchuk.rekorderr.features.ui.video.PhotoRekorderView
import javax.inject.Inject

@InjectViewState
class PhotoViewerPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val context: Context,
    private val router: AppRouter,
    private val photo: Rekord
) : BaseMvpPresenter<PhotoRekorderView>() {
    fun goBack() {
        router.openFeedCardsScreen(mutableListOf())
    }


}
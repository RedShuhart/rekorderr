package com.iyushchuk.rekorderr.features.ui.player

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import javax.inject.Inject

@InjectViewState
class RekordPlayerPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val context: Context,
    private val router: AppRouter,
    private val rekord: Rekord
) : BaseMvpPresenter<RekordPlayerView>() {
    fun goBack() {
        router.openFeedCardsScreen(mutableListOf())
    }
}
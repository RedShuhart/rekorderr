package com.iyushchuk.rekorderr.features.ui.splash

import android.util.Log
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import io.reactivex.Single
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val rekordRepository: RekordRepository,
    private val router: AppRouter
) : BaseMvpPresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        goToFeed()
    }

    fun goToFeed() {
        Single.just("")
            .delay(500, TimeUnit.MILLISECONDS)
            .compose(rxSchedulers.computationToMainSingle())
            .subscribe{ _ -> router.openFeedCardsScreen(mutableListOf()) }
            .unsubscribeOnDestroy()


    }
}
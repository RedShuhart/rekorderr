package com.iyushchuk.rekorderr.features.ui.splash

import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import timber.log.Timber
import javax.inject.Inject

class SplashPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val rekordRepository: RekordRepository,
    private val router: AppRouter
) : BaseMvpPresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFeed()
    }

    fun getFeed() {
        rekordRepository.getRekords()
            .compose(rxSchedulers.ioToMain())
            .progress()
            .subscribe(
                {result -> goToFeed(result)},
                {throwable -> Timber.e(throwable)}
            ).unsubscribeOnDestroy()

    }

    private fun goToFeed(rekords: List<Rekord>) {
        router.openFeedCardsScreen(rekords.toMutableList())
    }
}
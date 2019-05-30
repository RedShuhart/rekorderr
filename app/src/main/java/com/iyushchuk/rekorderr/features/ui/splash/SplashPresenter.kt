package com.iyushchuk.rekorderr.features.ui.splash

import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import javax.inject.Inject

class SplashPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val router: AppRouter
) : BaseMvpPresenter<SplashView>()
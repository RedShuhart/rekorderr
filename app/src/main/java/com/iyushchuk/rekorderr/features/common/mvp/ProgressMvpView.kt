package com.iyushchuk.rekorderr.features.common.mvp

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


interface ProgressMvpView : BaseMvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun showProgress()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun hideProgress()

}
package com.iyushchuk.rekorderr.features.common.mvp

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ScreenRotationView: BaseMvpView {

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun disableRotation()

    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun enableRotation()
}
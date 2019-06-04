package com.iyushchuk.rekorderr.features.ui.recorders.video

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import com.iyushchuk.rekorderr.features.common.mvp.ScreenRotationView
import java.io.File

interface VideoRekorderView: BaseMvpView, ScreenRotationView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startTakingVideo(file: File)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun stopTakingVideo()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeActionButtonState(state: Boolean)
}
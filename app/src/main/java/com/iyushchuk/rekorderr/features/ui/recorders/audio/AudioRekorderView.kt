package com.iyushchuk.rekorderr.features.ui.recorders.audio

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import com.iyushchuk.rekorderr.features.common.mvp.ScreenRotationView
import java.io.File

interface AudioRekorderView: BaseMvpView, ScreenRotationView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startRecordingAudio(file: File)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun stopRecordingAudio()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeActionButtonState(state: Boolean)
}
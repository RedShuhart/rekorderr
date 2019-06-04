package com.iyushchuk.rekorderr.features.ui.recorders.photo

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import com.iyushchuk.rekorderr.features.common.mvp.ScreenRotationView
import java.io.File

interface PhotoRekorderView: BaseMvpView, ScreenRotationView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun takePhoto(file: File)
}
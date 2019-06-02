package com.iyushchuk.rekorderr.features.ui.recorders.photo

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import java.io.File

interface PhotoRekorderView: BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun takePhoto(file: File)
}
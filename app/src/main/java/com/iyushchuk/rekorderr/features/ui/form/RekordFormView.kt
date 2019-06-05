package com.iyushchuk.rekorderr.features.ui.form

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import java.io.File

interface RekordFormView: BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun highlightText()
}
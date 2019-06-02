package com.iyushchuk.rekorderr.features.ui.video

import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import com.iyushchuk.rekorderr.features.ui.feed.FeedAdapter
import java.io.File

interface VideoRekorderView: BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startTakingVideo(file: File)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun stopTakingVideo()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun changeActionButtonState(state: Boolean)
}
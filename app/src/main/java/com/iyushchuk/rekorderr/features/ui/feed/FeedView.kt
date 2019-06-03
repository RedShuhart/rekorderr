package com.iyushchuk.rekorderr.features.ui.feed

import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpView
import com.iyushchuk.rekorderr.features.common.mvp.ProgressMvpView

interface FeedView: BaseMvpView, ProgressMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setAdapter(adapter: RecyclerView.Adapter<FeedAdapter.RekordViewHolder>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLayout(layoutType: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun askForPermissions(target: List<String>, code: Int)

}
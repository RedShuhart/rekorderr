package com.iyushchuk.rekorderr.features.ui.feed

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.entities.RekordType
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.core.shared.ViewType.Companion.LIST
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FeedPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val context: Context,
    private val rekordRepository: RekordRepository,
    private val router: AppRouter,
    private val feed: MutableList<Rekord>
) : BaseMvpPresenter<FeedView>() {

    val adapter = FeedAdapter(onClickListener = this::onFeedItemClick)
    var layoutType = LIST

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.hideProgress()
        viewState.setAdapter(adapter)
        viewState.setLayout(layoutType)
        getFeed()
    }

    fun onFeedItemClick(position: Int) {
        when(feed[position].rekordType) {
            RekordType.VIDEO -> goToVideoViewer()
            RekordType.PHOTO -> goToPhotoViewer()
            RekordType.AUDIO -> goToAudioViewer()
        }
    }

    fun switchLayout(type: Int) {
        layoutType = type
        viewState.setLayout(layoutType)
        adapter.viewType = layoutType
        adapter.notifyDataSetChanged()
    }

    private fun getFeed() {
        rekordRepository.getRekords()
            .compose(rxSchedulers.ioToMain())
            .progress()
            .subscribe(
                {result ->
                    feed.clear()
                    feed.addAll(result)
                    enrichAdapter(result.toMutableList())},
                {throwable -> Timber.e(throwable)}
            ).unsubscribeOnDestroy()
    }

    private fun enrichAdapter(feed: MutableList<Rekord>) {
        adapter.clear()
        adapter.addAll(feed)
        adapter.notifyDataSetChanged()
    }

    fun goToVideoMaker() {
    }

    fun goToPhotoMaker() {
    }

    fun goToAudioMaker() {
    }

    private fun goToVideoViewer() {
    }

    private fun goToPhotoViewer() {
    }

    private fun goToAudioViewer() {
    }

}
package com.iyushchuk.rekorderr.features.ui.feed

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.entities.RekordType
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.core.shared.AppPermissions
import com.iyushchuk.rekorderr.core.shared.AppPermissions.Companion.AUDIO_REQUEST_KEY
import com.iyushchuk.rekorderr.core.shared.AppPermissions.Companion.PHOTO_REQUEST_KEY
import com.iyushchuk.rekorderr.core.shared.AppPermissions.Companion.STORAGE_REQUEST_KEY
import com.iyushchuk.rekorderr.core.shared.AppPermissions.Companion.VIDEO_REQUEST_KEY
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

    private val adapter = FeedAdapter(
        onClickListener = this::onFeedItemClick,
        onLongClickListener = this::onLongFeedItemClick
    )

    private var layoutType = LIST

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setAdapter(adapter)
        viewState.setLayout(layoutType)
        getFeed()
    }

    fun switchLayout(type: Int) {
        layoutType = type
        viewState.setLayout(layoutType)
        adapter.viewType = layoutType
        adapter.notifyDataSetChanged()
    }


    fun goToVideoMaker() {
        val unsatisfiedPermissions = AppPermissions.getUnsatisfiedPermissions(context, VIDEO_REQUEST_KEY, STORAGE_REQUEST_KEY)

        if(unsatisfiedPermissions.isEmpty()) {
            router.openVideoRekorderScreen(Rekord(rekordType = RekordType.VIDEO))
        } else {
            viewState.askForPermissions(unsatisfiedPermissions, VIDEO_REQUEST_KEY)
        }
    }

    fun goToPhotoMaker() {
        val unsatisfiedPermissions = AppPermissions.getUnsatisfiedPermissions(context, PHOTO_REQUEST_KEY, STORAGE_REQUEST_KEY)

        if(unsatisfiedPermissions.isEmpty()) {
            router.openPhotoRekorderScreen(Rekord(rekordType = RekordType.PHOTO))
        } else {
            viewState.askForPermissions(unsatisfiedPermissions, PHOTO_REQUEST_KEY)
        }
    }

    fun goToAudioMaker() {
        val unsatisfiedPermissions = AppPermissions.getUnsatisfiedPermissions(context, AUDIO_REQUEST_KEY, STORAGE_REQUEST_KEY)

        if(unsatisfiedPermissions.isEmpty()) {
            router.openAudioRekorderScreen(Rekord(rekordType = RekordType.AUDIO))
        } else {
            viewState.askForPermissions(unsatisfiedPermissions, AUDIO_REQUEST_KEY)
        }
    }

    private fun onFeedItemClick(position: Int) {
        val rekord = feed[position]
        when(rekord.rekordType) {
            RekordType.VIDEO -> goToVideoViewer(rekord)
            RekordType.PHOTO -> goToPhotoViewer(rekord)
            RekordType.AUDIO -> goToAudioViewer(rekord)
        }
    }

    private fun onLongFeedItemClick(position: Int) {
        viewState.showDeleteDialog(feed[position])
    }


    private fun getFeed() {
        rekordRepository.getRekords()
            .compose(rxSchedulers.ioToMain())
            .progress()
            .subscribe(
                {result ->
                    viewState.hideProgress()
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

    fun deleteRekord(rekord: Rekord) {
        rekordRepository.deleteRekord(rekord)
            .compose(rxSchedulers.ioToMainCompletable())
            .subscribe(
                { viewState.hideDeleteDialog()
                    getFeed() },
                { throwable -> Timber.e(throwable) }
            ).unsubscribeOnDestroy()
    }

    private fun goToVideoViewer(rekord: Rekord) {
        router.openRekordPlayerScreen(rekord)
    }

    private fun goToPhotoViewer(rekord: Rekord) {
        router.openPhotoViewerScreen(rekord)
    }

    private fun goToAudioViewer(rekord: Rekord) {
        router.openRekordPlayerScreen(rekord)
    }

}
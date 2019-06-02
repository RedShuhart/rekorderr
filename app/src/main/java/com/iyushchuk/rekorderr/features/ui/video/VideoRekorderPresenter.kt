package com.iyushchuk.rekorderr.features.ui.video

import android.content.Context
import android.media.MediaMetadataRetriever
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.core.shared.RecordingState.Companion.RECORDING
import com.iyushchuk.rekorderr.core.shared.RecordingState.Companion.STOPPED
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import java.io.File

import javax.inject.Inject

@InjectViewState
class VideoRekorderPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val context: Context,
    private val router: AppRouter,
    private val video: Rekord
) : BaseMvpPresenter<VideoRekorderView>() {

    var recordingState = STOPPED

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.changeActionButtonState(recordingState)
    }

    fun changeState() {
        recordingState = !recordingState
        viewState.changeActionButtonState(recordingState)
        when (recordingState) {
            RECORDING -> viewState.startTakingVideo(File(video.getPath()))
            STOPPED -> viewState.stopTakingVideo()
        }
    }

    fun onVideoTaken() {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(video.getPath())
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        retriever.release()
        val timeInMillis = time.toLong()
        router.openRekordFormScreen(video.copy(length = timeInMillis))
    }


}
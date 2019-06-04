package com.iyushchuk.rekorderr.features.ui.recorders.audio

import android.content.Context
import android.media.MediaMetadataRetriever
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.core.shared.RecordingState
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import java.io.File
import javax.inject.Inject
import android.media.MediaRecorder



@InjectViewState
class AudioRekorderPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val context: Context,
    private val router: AppRouter,
    private val video: Rekord
) : BaseMvpPresenter<AudioRekorderView>() {

    var recordingState = RecordingState.STOPPED

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.changeActionButtonState(recordingState)
    }

    fun changeState() {
        recordingState = !recordingState
        viewState.changeActionButtonState(recordingState)
        when (recordingState) {
            RecordingState.RECORDING -> {
                viewState.disableRotation()
                viewState.startRecordingAudio(File(video.getPath()))
            }
            RecordingState.STOPPED -> {
                viewState.enableRotation()
                viewState.stopRecordingAudio()
            }
        }
    }

    fun onAudioRecorded() {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(video.getPath())
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        retriever.release()
        val timeInMillis = time.toLong()
        router.openRekordFormScreen(video.copy(length = timeInMillis))
    }


    fun goBack() {
        viewState.enableRotation()
        router.openFeedCardsScreen(mutableListOf())
    }

}
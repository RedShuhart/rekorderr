package com.iyushchuk.rekorderr.features.ui.recorders.audio

import android.content.pm.ActivityInfo
import android.media.MediaRecorder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.di.ui.fragments.AudioRekorderModule
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.shared.RecordingState
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import java.io.File
import javax.inject.Inject

class AudioRekorderFragment : BaseMvpFragment(), AudioRekorderView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AudioRekorderPresenter

    @ProvidePresenter
    fun providePresenter(): AudioRekorderPresenter = presenter

    private lateinit var actionButton: AppCompatImageButton

    private val audioRecorder = MediaRecorder().apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val audio: Rekord = arguments!!.getParcelable(AUDIO)!!
        getActivityComponent().plus(AudioRekorderModule(audio)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.audio_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionButton = view.findViewById(R.id.action_button)
        actionButton.setOnClickListener { presenter.changeState() }

    }

    override fun startRecordingAudio(file: File) {
        audioRecorder.setOutputFile(file)
        audioRecorder.prepare()
        audioRecorder.start()
    }

    override fun stopRecordingAudio() {
        audioRecorder.stop()
        audioRecorder.release()
        presenter.onAudioRecorded()
    }

    override fun changeActionButtonState(state: Boolean) {
        when(state) {
            RecordingState.STOPPED -> actionButton.setImageResource(R.drawable.record)
            RecordingState.RECORDING -> actionButton.setImageResource(R.drawable.stop)
        }
    }

    override fun disableRotation() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
    }

    override fun enableRotation() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }


    override fun onBackPressed(): Boolean {
        presenter.goBack()
        return true
    }

    companion object {

        private const val AUDIO = "AUDIO"

        fun newInstance(audio: Rekord): AudioRekorderFragment {

            val args = Bundle()
            args.putParcelable(AUDIO, audio)

            val fragment = AudioRekorderFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
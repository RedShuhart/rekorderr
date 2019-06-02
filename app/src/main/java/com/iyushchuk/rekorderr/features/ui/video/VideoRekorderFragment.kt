package com.iyushchuk.rekorderr.features.ui.video

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatImageButton
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.di.ui.fragments.VideoRekorderModule
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.shared.RecordingState.Companion.RECORDING
import com.iyushchuk.rekorderr.core.shared.RecordingState.Companion.STOPPED
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.VideoResult
import java.io.File

import javax.inject.Inject

class VideoRekorderFragment : BaseMvpFragment(), VideoRekorderView {


    @Inject
    @InjectPresenter
    lateinit var presenter: VideoRekorderPresenter

    @ProvidePresenter
    fun providePresenter(): VideoRekorderPresenter = presenter

    private lateinit var actionButton: AppCompatImageButton
    private lateinit var cameraView: CameraView

    override fun onCreate(savedInstanceState: Bundle?) {
        val video: Rekord = arguments!!.getParcelable(VIDEO)!!
        getActivityComponent().plus(VideoRekorderModule(video)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionButton = view.findViewById(R.id.action_button)
        actionButton.setOnClickListener { presenter.changeState() }

        cameraView = view.findViewById(R.id.video_rekorder)
        cameraView.setLifecycleOwner(viewLifecycleOwner)
        cameraView.addCameraListener(object : CameraListener() {
            override fun onVideoTaken(result: VideoResult) {
                presenter.onVideoTaken()
            }
        })
    }

    override fun startTakingVideo(file: File) {
        cameraView.takeVideo(file)
    }

    override fun stopTakingVideo() {
        cameraView.stopVideo()
    }

    override fun changeActionButtonState(state: Boolean) {
        when(state) {
            STOPPED -> actionButton.setImageResource(R.drawable.record)
            RECORDING -> actionButton.setImageResource(R.drawable.stop)
        }
    }



    companion object {

        private const val VIDEO = "VIDEO"

        fun newInstance(video: Rekord): VideoRekorderFragment {

            val args = Bundle()
            args.putParcelable(VIDEO, video)

            val fragment = VideoRekorderFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
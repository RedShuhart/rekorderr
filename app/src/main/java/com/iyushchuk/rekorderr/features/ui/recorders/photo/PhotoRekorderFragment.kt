package com.iyushchuk.rekorderr.features.ui.recorders.photo

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatImageButton
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.di.ui.fragments.PhotoRekorderModule
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import com.otaliastudios.cameraview.*
import java.io.File

import javax.inject.Inject

class PhotoRekorderFragment : BaseMvpFragment(), PhotoRekorderView {


    @Inject
    @InjectPresenter
    lateinit var presenter: PhotoRekorderPresenter

    @ProvidePresenter
    fun providePresenter(): PhotoRekorderPresenter = presenter

    private lateinit var actionButton: AppCompatImageButton
    private lateinit var cameraView: CameraView

    override fun onCreate(savedInstanceState: Bundle?) {
        val photo: Rekord = arguments!!.getParcelable(PHOTO)!!
        getActivityComponent().plus(PhotoRekorderModule(photo)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photo_video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionButton = view.findViewById(R.id.action_button)
        actionButton.setImageResource(R.drawable.camera)
        actionButton.setOnClickListener { presenter.onTakePhoto() }

        cameraView = view.findViewById(R.id.video_rekorder)
        initCameraView()
    }

    override fun onResume() {
        super.onResume()
        getBaseActivity().supportActionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        getBaseActivity().supportActionBar?.show()
    }

    override fun takePhoto(file: File) {
        cameraView.takePicture()
    }

    private fun initCameraView() {
        cameraView.apply {
            mode = Mode.PICTURE
            setLifecycleOwner(viewLifecycleOwner)
            addCameraListener(object : CameraListener() {
                override fun onPictureTaken(result: PictureResult) {
                    presenter.onPhotoTaken(result)
                }
            })
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

        private const val PHOTO = "PHOTO"

        fun newInstance(photo: Rekord): PhotoRekorderFragment {

            val args = Bundle()
            args.putParcelable(PHOTO, photo)

            val fragment = PhotoRekorderFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
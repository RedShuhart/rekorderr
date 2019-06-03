package com.iyushchuk.rekorderr.features.ui.photoviewer

import com.iyushchuk.rekorderr.core.di.ui.fragments.PhotoViwerModule
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatImageView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.shared.loadImage
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment


import javax.inject.Inject

class PhotoViewerFragment : BaseMvpFragment(), PhotoViewerView {


    @Inject
    @InjectPresenter
    lateinit var presenter: PhotoViewerPresenter

    @ProvidePresenter
    fun providePresenter(): PhotoViewerPresenter = presenter

    private lateinit var photoView: AppCompatImageView

    lateinit var photo: Rekord

    override fun onCreate(savedInstanceState: Bundle?) {
        photo = arguments!!.getParcelable(PHOTO)!!
        getActivityComponent().plus(PhotoViwerModule(photo)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photo_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoView = view.findViewById(R.id.photo)

        loadImage(requireContext(), photo, photoView)
    }

    override fun onBackPressed(): Boolean {
        presenter.goBack()
        return true
    }


    companion object {

        private const val PHOTO = "PHOTO"

        fun newInstance(photo: Rekord): PhotoViewerFragment {

            val args = Bundle()
            args.putParcelable(PHOTO, photo)

            val fragment = PhotoViewerFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
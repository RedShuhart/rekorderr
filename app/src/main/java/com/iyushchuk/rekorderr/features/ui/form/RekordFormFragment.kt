package com.iyushchuk.rekorderr.features.ui.recorders.video

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.textfield.TextInputEditText
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.di.ui.fragments.RekordFormModule
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.entities.RekordType
import com.iyushchuk.rekorderr.core.shared.loadImage
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import com.iyushchuk.rekorderr.features.ui.form.RekordFormPresenter
import com.iyushchuk.rekorderr.features.ui.form.RekordFormView

import javax.inject.Inject

class RekordFormFragment : BaseMvpFragment(), RekordFormView {


    @Inject
    @InjectPresenter
    lateinit var presenter: RekordFormPresenter

    @ProvidePresenter
    fun providePresenter(): RekordFormPresenter = presenter

    private lateinit var thumbnail: AppCompatImageView
    private lateinit var saveButton: AppCompatButton
    private lateinit var titleText: TextInputEditText
    private lateinit var typeIcon: AppCompatImageView

    lateinit var rekord: Rekord

    override fun onCreate(savedInstanceState: Bundle?) {
        rekord = arguments!!.getParcelable(REKORD)!!
        getActivityComponent().plus(RekordFormModule(rekord)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photo_video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        thumbnail = view.findViewById(R.id.thumbnail)
        saveButton = view.findViewById(R.id.save_button)
        titleText = view.findViewById(R.id.rekord_title)
        typeIcon = view.findViewById(R.id.type_icon)

        typeIcon.setImageDrawable(resolveTypeIcon(rekord, requireContext()))

        saveButton.setOnClickListener {
            presenter.saveRekord(titleText.text.toString())
        }

        when (rekord.rekordType) {
            RekordType.VIDEO, RekordType.PHOTO -> {
                loadImage(requireContext(), rekord, thumbnail)
            }
            RekordType.AUDIO -> {
                thumbnail.setImageDrawable(requireContext().resources.getDrawable(R.drawable.microphone_outline, null))
                typeIcon.visibility = View.INVISIBLE
            }
        }


    }

    override fun onBackPressed(): Boolean {
        presenter.cancel()
        return true
    }

    private fun resolveTypeIcon(item: Rekord, context: Context): Drawable =
        when(item.rekordType) {
            RekordType.VIDEO -> context.resources.getDrawable(R.drawable.video_outline, null)
            RekordType.AUDIO -> context.resources.getDrawable(R.drawable.microphone_outline, null)
            RekordType.PHOTO -> context.resources.getDrawable(R.drawable.image_outline, null)
        }


    companion object {

        private const val REKORD = "REKORD"

        fun newInstance(rekord: Rekord): RekordFormFragment {

            val args = Bundle()
            args.putParcelable(REKORD, rekord)

            val fragment = RekordFormFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
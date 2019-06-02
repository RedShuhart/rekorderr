package com.iyushchuk.rekorderr.features.ui.player

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatTextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.exoplayer2.ui.PlayerView
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.di.ui.fragments.RekordPlayerModule
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import javax.inject.Inject
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class RekordPlayerFragment : BaseMvpFragment(), RekordPlayerView {


    @Inject
    @InjectPresenter
    lateinit var presenter: RekordPlayerPresenter

    @ProvidePresenter
    fun providePresenter(): RekordPlayerPresenter = presenter

    lateinit var rekord: Rekord

    private lateinit var playerView: PlayerView
    private lateinit var player: SimpleExoPlayer
    private lateinit var title: AppCompatTextView

    private var playbackPosition: Long = 0
    private var currentWindow: Int = 0

    private var trackSelector: DefaultTrackSelector? = null
    private var lastSeenTrackGroupArray: TrackGroupArray? = null
    private val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        rekord = arguments!!.getParcelable(REKORD)!!
        getActivityComponent().plus(RekordPlayerModule(rekord)).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photo_video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerView = view.findViewById(R.id.player)

        title = view.findViewById(R.id.title)

        title.text = rekord.title
    }

    override fun onBackPressed(): Boolean {
        presenter.goBack()
        return true
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initializePlayer() {

        trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        mediaDataSourceFactory = DefaultDataSourceFactory(requireContext(), Util.getUserAgent(requireContext(), "Rekorderr"))

        val mediaSource = ProgressiveMediaSource.Factory(mediaDataSourceFactory)
            .createMediaSource(Uri.parse(rekord.getPath()))

        player = ExoPlayerFactory.newSimpleInstance(requireContext(), trackSelector)


        with(player) {
            prepare(mediaSource, false, false)
            playWhenReady = true
        }

        playerView.player = player
        playerView.requestFocus()
        lastSeenTrackGroupArray = null
    }


    private fun updateStartPosition() {

        with(player) {
            playbackPosition = currentPosition
            currentWindow = currentWindowIndex
            playWhenReady = playWhenReady
        }
    }

    private fun releasePlayer() {
        updateStartPosition()
        player.release()
        trackSelector = null
    }


    companion object {

        private const val REKORD = "REKORD"

        fun newInstance(rekord: Rekord): RekordPlayerFragment {

            val args = Bundle()
            args.putParcelable(REKORD, rekord)

            val fragment = RekordPlayerFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
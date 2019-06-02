package com.iyushchuk.rekorderr.features.ui.feed

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.di.ui.fragments.FeedModule
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.shared.ViewType.Companion.GRID
import com.iyushchuk.rekorderr.core.shared.ViewType.Companion.LIST
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpFragment
import javax.inject.Inject

class FeedFragment : BaseMvpFragment(), FeedView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FeedPresenter

    @ProvidePresenter
    fun providePresenter(): FeedPresenter = presenter

    private lateinit var feedView: RecyclerView
    private lateinit var progressBar: ContentLoadingProgressBar

    private val gridLayoutManager by lazy {
        GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
    }

    private val listLayoutManager by lazy {
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val feed: MutableList<Rekord> = arguments!!.getParcelableArrayList(FEED) ?: mutableListOf()
        getActivityComponent().plus(FeedModule(feed)).inject(this)
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)
        feedView = view.findViewById(R.id.feed)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.grid -> {
                presenter.switchLayout(GRID)
                true
            }
            R.id.list -> {
                presenter.switchLayout(LIST)
                true
            }
            R.id.record_video -> {
                presenter.goToVideoMaker()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<FeedAdapter.RekordViewHolder>) {
        feedView.adapter = adapter
    }

    override fun setLayout(layoutType: Int) {
        feedView.layoutManager = when (layoutType) {
            GRID -> gridLayoutManager
            LIST -> listLayoutManager
            else -> listLayoutManager
        }
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }


    companion object {

        private const val FEED = "FEED"

        fun newInstance(feed: List<Rekord>): FeedFragment {

            val args = Bundle()
            args.putParcelableArrayList(FEED, ArrayList(feed))

            val fragment = FeedFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
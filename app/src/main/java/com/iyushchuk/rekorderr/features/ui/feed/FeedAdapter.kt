package com.iyushchuk.rekorderr.features.ui.feed

import android.content.Context
import android.graphics.drawable.Drawable
import com.iyushchuk.rekorderr.R
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.iyushchuk.rekorderr.core.domain.entities.RekordType
import com.iyushchuk.rekorderr.core.shared.ViewType.Companion.GRID
import com.iyushchuk.rekorderr.core.shared.ViewType.Companion.LIST
import com.iyushchuk.rekorderr.core.shared.loadImage
import com.iyushchuk.rekorderr.core.shared.loadImageWithInfo
import com.iyushchuk.rekorderr.core.shared.millisToLengthString
import com.iyushchuk.rekorderr.core.shared.printInstant

class FeedAdapter(
    private val data: MutableList<Rekord> = mutableListOf(),
    val onClickListener: (position: Int) -> Unit = {}
): RecyclerView.Adapter<FeedAdapter.RekordViewHolder>(), MutableList<Rekord> by data {

    public var viewType = LIST

    override fun onBindViewHolder(holder: RekordViewHolder, position: Int) {
        val item = get(position)
        holder.bindTo(item, position, holder.itemView.context, onClickListener)
    }

    override fun getItemViewType(position: Int) = viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekordViewHolder {
        val (holder, view) = when (viewType) {
            GRID -> Pair(RekordViewHolder::GridViewHolder, LayoutInflater.from(parent.context).inflate(R.layout.feed_grid_item, parent, false))
            LIST -> Pair(RekordViewHolder::ListViewHolder, LayoutInflater.from(parent.context).inflate(R.layout.feed_list_item, parent, false))
            else -> Pair(RekordViewHolder::ListViewHolder, LayoutInflater.from(parent.context).inflate(R.layout.feed_list_item, parent, false))
        }
        return holder(view)
    }

    override fun getItemCount() = data.size

    sealed class RekordViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bindTo(
            item: Rekord,
            position: Int,
            context: Context,
            onClickListener: (position: Int) -> Unit = {}
        )

        class GridViewHolder(view: View) : RekordViewHolder(view) {
            private val thumbnail: AppCompatImageView = view.findViewById(R.id.thumbnail)
            private val itemInfo: AppCompatTextView = view.findViewById(R.id.info)
            private val type: AppCompatImageView = view.findViewById(R.id.type)

            override fun bindTo(
                item: Rekord,
                position: Int,
                context: Context,
                onClickListener: (position: Int) -> Unit
            ) {
                type.setImageDrawable(resolveTypeIcon(item, context))
                resolveThumbnailInfo(item, itemInfo, thumbnail, context)
                thumbnail.setOnClickListener {
                    onClickListener(position)
                }
            }
        }

        class ListViewHolder(view: View) : RekordViewHolder(view) {
            private val thumbnail: AppCompatImageView = view.findViewById(R.id.thumbnail)
            private val title: AppCompatTextView = view.findViewById(R.id.title)
            private val createdAt: AppCompatTextView = view.findViewById(R.id.created_at)
            private val type: AppCompatImageView = view.findViewById(R.id.type)
            private val itemInfo: AppCompatTextView = view.findViewById(R.id.info)
            private val feedCard: CardView = view.findViewById(R.id.feed_card)

            override fun bindTo(
                item: Rekord,
                position: Int,
                context: Context,
                onClickListener: (position: Int) -> Unit
            ) {
                title.text = item.title
                createdAt.text = printInstant(item.createdAt)
                type.setImageDrawable(resolveTypeIcon(item, context))
                resolveThumbnailInfo(item, itemInfo, thumbnail, context)
                feedCard.setOnClickListener {
                    onClickListener(position)
                }
            }
        }

        protected fun resolveTypeIcon(item: Rekord, context: Context): Drawable =
            when(item.rekordType) {
                RekordType.VIDEO -> context.resources.getDrawable(R.drawable.video_outline, null)
                RekordType.AUDIO -> context.resources.getDrawable(R.drawable.microphone_outline, null)
                RekordType.PHOTO -> context.resources.getDrawable(R.drawable.image_outline, null)
            }

        protected fun resolveThumbnailInfo(item: Rekord, itemInfo: AppCompatTextView, thumbnail: AppCompatImageView, context: Context) =
            when (item.rekordType) {
                RekordType.VIDEO, RekordType.AUDIO -> {
                    loadImage(context, item, thumbnail)
                    itemInfo.text = millisToLengthString(item.length)
                }
                RekordType.PHOTO -> loadImageWithInfo(context, item, thumbnail) { info -> itemInfo.text = info }
            }
    }


}
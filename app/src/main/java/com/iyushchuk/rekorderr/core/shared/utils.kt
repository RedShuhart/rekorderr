package com.iyushchuk.rekorderr.core.shared

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.entities.RekordType
import java.time.Instant
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.github.piasy.rxandroidaudio.PlayConfig.uri





fun getFileDirectory(type: RekordType): String
        = Environment.getExternalStoragePublicDirectory(type.directory).absolutePath

fun printInstant(instant: Instant): String = printFormatter
    .withZone(systemDefault())
    .format(instant)

internal val printFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")

fun loadImage(context: Context, rekord: Rekord, imageView: AppCompatImageView) =
    Glide.with(context)
        .load(rekord.getPath())
        .placeholder(createCircularProgressDrawable(context))
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)


fun loadImageWithInfo(context: Context, rekord: Rekord, imageView: AppCompatImageView, infoCallback: (info: String) -> Unit = {}) {
    Glide.with(context)
        .asBitmap()
        .load(rekord.getPath())
        .placeholder(createCircularProgressDrawable(context))
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imageView.setImageBitmap(resource)
                infoCallback("${resource.width}x${resource.height}")
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
}

internal fun createCircularProgressDrawable(context: Context) = CircularProgressDrawable(context).apply {
    strokeWidth = 10f
    centerRadius = 60f
    start()
}

fun millisToLengthString(millis: Long?): String {
    millis ?: return ""
    val minutes = millis / 1000 / 60
    val seconds = millis / 1000 % 60

    val minString = if (minutes > 0) "$minutes min" else ""
    val secString = if (seconds > 0) "$seconds s" else ""

    return "$minString : $secString"
}

class ViewType {
    companion object {
        const val GRID = 0
        const val LIST = 1
    }
}


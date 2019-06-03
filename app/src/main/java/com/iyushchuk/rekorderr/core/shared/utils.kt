package com.iyushchuk.rekorderr.core.shared

import android.Manifest
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
import com.bumptech.glide.request.transition.Transition
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat




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

class RecordingState {
    companion object {
        const val STOPPED = false
        const val RECORDING = true
    }
}

sealed class AppPermissions {
    companion object {

        const val AUDIO_REQUEST_KEY = 1
        const val VIDEO_REQUEST_KEY = 2
        const val PHOTO_REQUEST_KEY = 3
        const val STORAGE_REQUEST_KEY = 4

        private val permissions = mapOf(
            AUDIO_REQUEST_KEY to listOf(Manifest.permission.RECORD_AUDIO),
            VIDEO_REQUEST_KEY to listOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA),
            PHOTO_REQUEST_KEY to listOf(Manifest.permission.CAMERA),
            STORAGE_REQUEST_KEY to listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

        fun getPermission(key: Int) = permissions[key]

        fun getUnsatisfiedPermissions(context: Context, vararg keys: Int) = permissions
            .filterKeys { it in keys }
            .values
            .toList()
            .flatten()
            .distinct()
            .filter { ActivityCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED }
    }
}



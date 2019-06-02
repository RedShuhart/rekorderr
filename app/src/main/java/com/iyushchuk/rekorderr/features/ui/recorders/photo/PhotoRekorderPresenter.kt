package com.iyushchuk.rekorderr.features.ui.recorders.photo

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import com.otaliastudios.cameraview.PictureResult
import java.io.File

import javax.inject.Inject

@InjectViewState
class PhotoRekorderPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val context: Context,
    private val router: AppRouter,
    private val photo: Rekord
) : BaseMvpPresenter<PhotoRekorderView>() {


    fun onPhotoTaken(result: PictureResult) {
        result.toFile(File(photo.getPath())) {}
        router.openRekordFormScreen(photo)
    }

    fun onTakePhoto() {
        viewState.takePhoto(File(photo.getPath()))
    }


}
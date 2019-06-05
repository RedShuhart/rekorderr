package com.iyushchuk.rekorderr.features.ui.form

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.iyushchuk.rekorderr.core.domain.entities.Rekord
import com.iyushchuk.rekorderr.core.domain.repository.RekordRepository
import com.iyushchuk.rekorderr.core.navigation.AppRouter
import com.iyushchuk.rekorderr.core.schedulers.RxSchedulers
import com.iyushchuk.rekorderr.features.common.mvp.BaseMvpPresenter
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@InjectViewState
class RekordFormPresenter @Inject internal constructor(
    private val rxSchedulers: RxSchedulers,
    private val rekordRepository: RekordRepository,
    private val context: Context,
    private val router: AppRouter,
    private val rekord: Rekord
) : BaseMvpPresenter<RekordFormView>() {

    fun saveRekord(text: String) {
        if(validate(text)) {
            val finalRekord = rekord.copy(title = text)
            rekordRepository.saveRekord(finalRekord)
                .compose(rxSchedulers.ioToMainCompletable())
                .subscribe {
                    router.openFeedCardsScreen(mutableListOf(finalRekord))
                }.unsubscribeOnDestroy()
        } else {
            viewState.highlightText()
        }
    }

    fun cancel() {
        val file = File(rekord.getPath())
        file.delete()
        router.openFeedCardsScreen(mutableListOf())
    }

    private fun validate(text: String): Boolean {
        val correctPattern = Regex("[A-Za-z0-9 ]+").matches(text)
        val correctLength = text.length <= 20
        return correctLength && correctPattern
    }
}
package com.iyushchuk.rekorderr.features.common.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseMvpPresenter<View: MvpView>: MvpPresenter<View>() {
    private val disposables = CompositeDisposable()

    protected fun Disposable.unsubscribeOnDestroy(): Disposable {
        disposables.add(this)
        return this
    }

    protected fun <T> Observable<T>.progress(): Observable<T> {
        return doOnSubscribe {
            showProgress()
        }.doAfterTerminate {
            hideProgress()
        }
    }

    private fun showProgress() {
        (viewState as? ProgressMvpView)?.showProgress()
    }

    private fun hideProgress() {
        (viewState as? ProgressMvpView)?.hideProgress()
    }


    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}

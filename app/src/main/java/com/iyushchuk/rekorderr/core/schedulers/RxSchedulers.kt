package com.iyushchuk.rekorderr.core.schedulers

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

abstract class RxSchedulers {

    abstract fun mainThread(): Scheduler

    abstract fun io(): Scheduler

    abstract fun computation(): Scheduler

    fun <T> ioToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { objectObservable ->
            objectObservable
                .subscribeOn(io())
                .observeOn(mainThread())
        }
    }

    fun <T> computationToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { objectObservable ->
            objectObservable
                .subscribeOn(computation())
                .observeOn(mainThread())
        }
    }
}
package com.iyushchuk.rekorderr.features.common.mvp

import com.iyushchuk.rekorderr.features.common.BaseActivity


open class BaseMvpFragment: MvpAppCompatFragment(), BaseMvpView {

    protected fun getActivityComponent() = getBaseActivity().getActivityComponent()

    protected fun getBaseActivity() = requireActivity() as BaseActivity

    open fun onBackPressed(): Boolean {
        return false
    }

}
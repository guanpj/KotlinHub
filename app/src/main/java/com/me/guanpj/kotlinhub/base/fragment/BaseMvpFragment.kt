package com.me.guanpj.kotlinhub.base.fragment

import android.os.Bundle
import android.view.View
import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), IMvpView {

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttatch(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }
}
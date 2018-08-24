package com.me.guanpj.kotlinhub.base.fragment

import android.os.Bundle
import android.view.View
import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import com.me.guanpj.kotlinhub.widget.view.DataLoadStatusView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_common_list.*
import javax.inject.Inject

abstract class BaseMvpFragment<out P : BasePresenter<*>> : BaseFragment(), IMvpView {

    @Inject
    lateinit var presenter: @UnsafeVariance P

    protected val statusView by lazy {
        DataLoadStatusView(rootView)
    }

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

    protected open fun showError(error: String){
        statusView.show(error)
    }

    protected open fun dismissError(){
        statusView.dismiss()
    }
}
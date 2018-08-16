package com.me.guanpj.kotlinhub.base.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.me.guanpj.kotlinhub.base.BasePresenter
import com.me.guanpj.kotlinhub.base.IMvpView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseMvpActivity<P : BasePresenter<*>>: BaseActivity(), IMvpView, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter: P

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = mFragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.onAttatch(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
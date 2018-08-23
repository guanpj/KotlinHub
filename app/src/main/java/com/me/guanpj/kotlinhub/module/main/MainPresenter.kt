package com.me.guanpj.kotlinhub.module.main

import com.me.guanpj.kotlinhub.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View>(), MainContract.Presenter {
}
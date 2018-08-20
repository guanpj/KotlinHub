package com.me.guanpj.kotlinhub.module.splash

import android.os.Bundle
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.activity.BaseActivity
import com.me.guanpj.kotlinhub.core.AppStatus
import com.me.guanpj.kotlinhub.core.AppStatusTracker
import com.me.guanpj.kotlinhub.module.login.LoginActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppStatusTracker.INSTANCE!!.setAppStatus(AppStatus.STATUS_ONLINE)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initView() {
    }

    override fun initDataAndEvent() {
        Observable.timer(1, TimeUnit.MILLISECONDS).subscribe { jumpToActivity(LoginActivity::class) }
    }

}

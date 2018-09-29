package com.me.guanpj.kotlinhub.module.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.activity.BaseActivity
import com.me.guanpj.kotlinhub.core.AccountManager
import com.me.guanpj.kotlinhub.core.AppStatus
import com.me.guanpj.kotlinhub.core.AppStatusTracker
import com.me.guanpj.kotlinhub.module.login.LoginActivity
import com.me.guanpj.kotlinhub.module.main.MainActivity
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

    @SuppressLint("CheckResult")
    override fun initDataAndEvent() {
        Observable.timer(50, TimeUnit.MILLISECONDS).subscribe {
            run {
                if (TextUtils.isEmpty(AccountManager.userName))
                    LoginActivity::class else MainActivity::class
            }.let(this::jumpToActivity)
            finish()
        }
    }

}

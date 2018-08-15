package com.me.guanpj.kotlinhub.base.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.me.guanpj.kotlinhub.MainActivity
import com.me.guanpj.kotlinhub.SplashActivity
import com.me.guanpj.kotlinhub.core.AppStatus
import com.me.guanpj.kotlinhub.core.AppStatusTracker

abstract class BaseActivity : AppCompatActivity() {
    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        when (AppStatusTracker.instance!!.getAppStatus()) {
            AppStatus.STATUS_FORCE_KILLED -> protectApp()
            AppStatus.STATUS_KICK_OUT -> kickOut()
            AppStatus.STATUS_LOGOUT, AppStatus.STATUS_OFFLINE, AppStatus.STATUS_ONLINE -> {
                initView()
                initDataAndEvent()
            }
        }
    }

    protected fun protectApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(AppStatus.KEY_HOME_ACTION, AppStatus.ACTION_RESTART_APP)
        startActivity(intent)
    }

    protected fun kickOut() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.putExtra(AppStatus.KEY_HOME_ACTION, AppStatus.ACTION_KICK_OUT)
        startActivity(intent)
    }

    abstract fun initView()

    abstract fun initDataAndEvent()
}
package com.me.guanpj.kotlinhub.base.activity

import android.content.Intent
import android.os.Bundle
import com.me.guanpj.kotlinhub.core.AppStatus
import com.me.guanpj.kotlinhub.core.AppStatusTracker
import com.me.guanpj.kotlinhub.module.main.MainActivity
import com.me.guanpj.kotlinhub.module.splash.SplashActivity
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity
import kotlin.reflect.KClass

abstract class BaseActivity : SwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setSwipeBackEnable(false)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        when (AppStatusTracker.INSTANCE!!.getAppStatus()) {
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

    fun jumpToActivity(target: KClass<*>) {
        jumpToActivity(target, null)
    }

    fun jumpToActivity(target: KClass<*>, data: Bundle?) {
        startActivity(Intent(this, target.java).apply { if (null != data) putExtras(data) })
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initDataAndEvent()
}
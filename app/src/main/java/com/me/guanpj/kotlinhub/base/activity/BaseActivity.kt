package com.me.guanpj.kotlinhub.base.activity

import android.content.Intent
import android.os.Bundle
import com.me.guanpj.kotlinhub.base.IBaseView
import com.me.guanpj.kotlinhub.core.AppStatus
import com.me.guanpj.kotlinhub.core.AppStatusTracker
import com.me.guanpj.kotlinhub.module.main.MainActivity
import com.me.guanpj.kotlinhub.module.splash.SplashActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity
import org.jetbrains.anko.indeterminateProgressDialog

abstract class BaseActivity : SwipeBackActivity(), IBaseView {

    val dialog by lazy { indeterminateProgressDialog("请稍候...") }
    private val compositeDisposable by lazy { CompositeDisposable() }

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

    override fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun removeDisposable(disposable: Disposable) {
        compositeDisposable.remove(disposable)
    }

    override fun deleteDisposable(disposable: Disposable) {
        compositeDisposable.delete(disposable)
    }

    override fun showDialog() {
        dialog.show()
    }

    override fun hideDialog() {
        dialog.hide()
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initDataAndEvent()
}
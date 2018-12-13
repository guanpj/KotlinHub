package com.me.guanpj.kotlinhub.core

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle

class AppStatusTracker private constructor(private val application: Application) : Application.ActivityLifecycleCallbacks {
    private val allActivities: MutableSet<Activity> by lazy { HashSet<Activity>() }
    private var appStatus = AppStatus.STATUS_FORCE_KILLED
    var isForground: Boolean = false
    private var activeCount: Int = 0
    private var timestamp: Long = 0
    private var isScreenOff: Boolean = false
    private var receiver: DeamonReceiver? = null

    companion object {
        @Volatile
        var INSTANCE: AppStatusTracker? = null

        fun init(application: Application) {
            INSTANCE = AppStatusTracker(application)
            application.registerActivityLifecycleCallbacks(INSTANCE)
        }
    }

    fun getAppStatus() = this.appStatus

    fun setAppStatus(status: Int) {
        this.appStatus = status
        if (this.appStatus == AppStatus.STATUS_ONLINE) {
            if (this.receiver == null) {
                val filter = IntentFilter()
                filter.addAction(Intent.ACTION_SCREEN_OFF)
                receiver = DeamonReceiver()
                this.application.registerReceiver(receiver, filter)
            }
        } else {
            this.application.unregisterReceiver(receiver)
            receiver = null
        }
    }

    private fun onScreenOff(isScreenOff: Boolean) {
        this.isScreenOff = isScreenOff
    }

    override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
        addActivity(activity)
    }

    override fun onActivityStarted(activity: Activity?) {
        activeCount++
    }

    override fun onActivityResumed(activity: Activity?) {
        isForground = true
        timestamp = 0L
        isScreenOff = false
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityStopped(activity: Activity?) {
        activeCount--
        if (activeCount == 0) {
            isForground = false
            timestamp = System.currentTimeMillis()
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {
        removeActivity(activity)
    }

    fun addActivity(act: Activity?) {
        act?.let { allActivities.add(it) }
    }

    fun removeActivity(act: Activity?) {
        act?.let { allActivities.remove(it) }
    }

    fun exitApp() {
        @Synchronized
        for (act in allActivities) {
            act.finish()
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    private inner class DeamonReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Intent.ACTION_SCREEN_OFF == intent.action) AppStatusTracker.INSTANCE?.onScreenOff(true)
        }
    }
}

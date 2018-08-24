package com.me.guanpj.kotlinhub.base.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment
import kotlin.reflect.KClass

abstract class BaseFragment : SwipeBackFragment() {

    lateinit var mContentView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContentView = inflater.inflate(getLayoutResId(), container, false)
        return attachToSwipeBack(mContentView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeBackEnable(false)
        initView(view)
        initDataAndEvent()
    }

    fun jumpToActivity(target: KClass<*>) {
        jumpToActivity(target, null)
    }

    fun jumpToActivity(target: KClass<*>, data: Bundle?) {
        startActivity(Intent(activity, target.java).apply { if (null != data) putExtras(data) })
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView(view: View)

    abstract fun initDataAndEvent()
}
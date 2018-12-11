package com.me.guanpj.kotlinhub.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.kotlinhub.base.IBaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

abstract class BaseFragment : SwipeBackFragment(), IBaseView {

    val dialog by lazy { indeterminateProgressDialog("请稍候...") }
    private val compositeDisposable by lazy { CompositeDisposable() }
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

    abstract fun initView(view: View)

    abstract fun initDataAndEvent()
}
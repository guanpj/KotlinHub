package com.me.guanpj.kotlinhub.widget.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import org.jetbrains.anko.*

class DataLoadStatusView(val parentView: ViewGroup) : _RelativeLayout(parentView.context) {

    private var textView: TextView

    var isShowing = false

    init {
        backgroundColor = Color.WHITE
        textView = textView {
            textSize = 18f
            textColor = Color.BLACK
            padding = dip(5)
        }.lparams {
            centerInParent()
        }
    }

    fun show(text: String) {
        if (!isShowing) {
            parentView.addView(this, matchParent, matchParent)
            alpha = 0f
            animate().alpha(1f).setDuration(100).start()
            isShowing = true
        }
        textView.text = text
    }

    fun dismiss() {
        if (isShowing) {
            parentView.startViewTransition(this)
            parentView.removeView(this)
            animate().alpha(0f).setDuration(100).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    parentView.endViewTransition(this@DataLoadStatusView)
                }
            }).start()
            isShowing = false
        }
    }

    fun show(@StringRes textRes: Int) {
        show(context.getString(textRes))
    }
}
package com.me.guanpj.kotlinhub.module.adapter

import android.support.annotation.LayoutRes
import android.support.v4.view.ViewCompat
import android.view.MotionEvent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.jetbrains.anko.dip

abstract class CommonListAdapter<D>(@LayoutRes layoutResId: Int) : BaseQuickAdapter<D, BaseViewHolder>(layoutResId) {

    companion object {
        private const val CARD_TAP_DURATION = 100L
    }

    init {
        setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    ViewCompat.animate(holder.itemView)
                            .scaleX(1.03f).scaleY(1.03f)
                            .translationZ(holder.itemView.dip(10).toFloat())
                            .duration = CARD_TAP_DURATION
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    super.setOnItemClick(holder.itemView, position)
                    ViewCompat.animate(holder.itemView)
                            .scaleX(1f).scaleY(1f)
                            .translationZ(holder.itemView.dip(0).toFloat())
                            .duration = CARD_TAP_DURATION
                }
                MotionEvent.ACTION_CANCEL -> {
                    ViewCompat.animate(holder.itemView)
                            .scaleX(1f).scaleY(1f)
                            .translationZ(holder.itemView.dip(0).toFloat())
                            .duration = CARD_TAP_DURATION
                }
            }
            false
        }
    }
}
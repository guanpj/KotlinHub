package com.me.guanpj.kotlinhub.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.style.LineBackgroundSpan
import android.text.style.LineHeightSpan
import android.text.style.ReplacementSpan

class EllipsizeLineSpan(private val mBottomMargin: Int) : ReplacementSpan(), LineBackgroundSpan, LineHeightSpan {
    private val mClipRect = Rect()

    override fun drawBackground(c: Canvas, p: Paint, left: Int, right: Int, top: Int,
                                baseline: Int, bottom: Int, text: CharSequence, start: Int, end: Int, lnum: Int) {
        c.getClipBounds(mClipRect)
    }

    override fun getSize(paint: Paint, text: CharSequence,
                         start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        if (fm != null) {
            paint.getFontMetricsInt(fm)
        }
        val textWidth = Math.ceil(paint.measureText(text, start, end).toDouble()).toInt()
        return Math.min(textWidth, mClipRect.width())
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int,
                      x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        var end = end
        val textWidth = paint.measureText(text, start, end)

        if (x + Math.ceil(textWidth.toDouble()).toInt() < mClipRect.right) {
            //text fits
            canvas.drawText(text, start, end, x, y.toFloat(), paint)
        } else {
            val ellipsisWidth = paint.measureText("\u2026")
            // move 'end' to the ellipsis point
            end = start + paint.breakText(text, start, end, true,
                    mClipRect.right.toFloat() - x - ellipsisWidth, null)
            canvas.drawText(text, start, end, x, y.toFloat(), paint)
            canvas.drawText("\u2026", x + paint.measureText(text, start, end), y.toFloat(), paint)
        }
    }

    override fun chooseHeight(text: CharSequence, start: Int, end: Int, spanstartv: Int,
                              v: Int, fm: Paint.FontMetricsInt) {
        fm.descent += mBottomMargin
        fm.bottom += mBottomMargin
    }
}
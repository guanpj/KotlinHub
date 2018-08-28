package com.me.guanpj.kotlinhub.util

import java.util.*

object CommonUtil {
    fun getLocale(language: String): Locale {
        val locale: Locale
        if (language.equals("zh-rCN", ignoreCase = true)) {
            return Locale.SIMPLIFIED_CHINESE
        } else if (language.equals("zh-rTW", ignoreCase = true)) {
            return Locale.TRADITIONAL_CHINESE
        }
        val array = language.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (array.size > 1) {
            locale = Locale(array[0], array[1])
        } else {
            locale = Locale(language)
        }
        return locale
    }
}
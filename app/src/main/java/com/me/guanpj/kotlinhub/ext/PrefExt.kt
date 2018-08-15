package com.me.guanpj.kotlinhub.ext

import com.bennyhuo.github.common.sharedpreferences.Preference
import com.me.guanpj.kotlinhub.Application
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) = Preference(Application.AppContext, "", default, R::class.jvmName)
package com.me.guanpj.kotlinhub.ext

import com.me.guanpj.kotlinhub.GithubApplication
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.pref(default: T) =
        Preference(GithubApplication.AppContext, "", default, R::class.jvmName)
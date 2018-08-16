package com.me.guanpj.kotlinhub.core

import com.google.gson.Gson
import com.me.guanpj.kotlinhub.entity.User
import com.me.guanpj.kotlinhub.ext.fromJson
import com.me.guanpj.kotlinhub.ext.pref

object AccountManager {
    var authId by pref(-1)
    var username by pref("")
    var passwd by pref("")
    var token by pref("")

    private var userJson by pref("")

    var currentUser: User? = null
        get() {
            if (field == null && userJson.isNotEmpty()) {
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            if (value == null) {
                userJson = ""
            } else {
                userJson = Gson().toJson(value)
            }
            field = value
        }

    fun isLoggedIn(): Boolean = token.isNotEmpty()
}
package com.me.guanpj.kotlinhub.data.remote.model

import com.google.gson.Gson
import com.me.guanpj.kotlinhub.data.remote.service.AuthService
import com.me.guanpj.kotlinhub.data.remote.service.UserService
import com.me.guanpj.kotlinhub.entity.AuthorizationReq
import com.me.guanpj.kotlinhub.entity.AuthorizationRsp
import com.me.guanpj.kotlinhub.entity.User
import com.me.guanpj.kotlinhub.ext.fromJson
import com.me.guanpj.kotlinhub.ext.pref
import io.reactivex.Observable
import retrofit2.HttpException

interface OnAccountStateChangeListener {
    fun onLogin(user: User)

    fun onLogout()
}

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

    val onAccountStateChangeListeners = ArrayList<OnAccountStateChangeListener>()

    private fun notifyLogin(user: User) {
        onAccountStateChangeListeners.forEach {
            it.onLogin(user)
        }
    }

    private fun notifyLogout() {
        onAccountStateChangeListeners.forEach { it.onLogout() }
    }

    fun isLoggedIn(): Boolean = token.isNotEmpty()

    fun login() =
            AuthService.createAuthorization(AuthorizationReq())
                    .doOnNext {
                        if (it.token.isEmpty()) throw AccountException(it)
                    }
                    .retryWhen {
                        it.flatMap {
                            if (it is AccountException) {
                                AuthService.deleteAuthorization(it.authorizationRsp.id)
                            } else {
                                Observable.error(it)
                            }
                        }
                    }
                    .flatMap {
                        token = it.token
                        authId = it.id
                        UserService.getAuthenticatedUser()
                    }
                    .map {
                        currentUser = it
                        notifyLogin(it)
                    }

    fun logout() = AuthService.deleteAuthorization(authId)
            .doOnNext {
                if (it.isSuccessful) {
                    authId = -1
                    token = ""
                    currentUser = null
                    notifyLogout()
                } else {
                    throw HttpException(it)
                }
            }

    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")
}
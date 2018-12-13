package com.me.guanpj.kotlinhub.core

object AppStatus {
    const val STATUS_FORCE_KILLED = -1
    const val STATUS_LOGOUT = 0
    const val STATUS_OFFLINE = 1
    const val STATUS_ONLINE = 2
    const val STATUS_KICK_OUT = 3

    const val KEY_HOME_ACTION = "key_home_action"
    const val ACTION_BACK_TO_HOME = 0
    const val ACTION_RESTART_APP = 1
    const val ACTION_LOGOUT = 2
    const val ACTION_KICK_OUT = 3
}

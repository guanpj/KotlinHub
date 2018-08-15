package com.me.guanpj.kotlinhub.ext

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val loggerMap = HashMap<Class<*>, Logger>()

inline val <reified T> T.logger: Logger
    get() {
        return loggerMap[T::class.java]?: LoggerFactory.getLogger(T::class.java).apply { loggerMap[T::class.java] = this }
    }
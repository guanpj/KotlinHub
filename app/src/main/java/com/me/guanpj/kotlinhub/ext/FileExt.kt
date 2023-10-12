package com.me.guanpj.kotlinhub.ext

import android.util.Log
import java.io.File

private const val TAG = "FileExt"

fun File.ensureDir(): Boolean {
    try {
        isDirectory.no {
            isFile.yes {
                delete()
            }
            return mkdirs()
        }
    } catch (e: Exception) {
        e.message?.let {
            Log.w(TAG, it)
        }
    }
    return false
}
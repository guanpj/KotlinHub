package com.me.guanpj.kotlinhub.entity

import android.graphics.Color
import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo

@PoKo
data class Label(var id: Long,
                 var name: String?,
                 var color: String?,
                 @SerializedName("default") var isDefault: Boolean,
                 var isSelect: Boolean = false) {


    fun getColorValue(): Int {
        try {
            return Color.parseColor("#" + color!!)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return 0
        }
    }

}

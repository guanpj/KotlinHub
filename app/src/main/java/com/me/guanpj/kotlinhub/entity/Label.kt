package com.me.guanpj.kotlinhub.entity

import android.graphics.Color
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo
import kotlinx.android.parcel.Parcelize

@PoKo
@Parcelize
data class Label(var id: Long,
                 var name: String?,
                 var color: String?,
                 @SerializedName("default") var isDefault: Boolean,
                 var isSelect: Boolean = false) : Parcelable {


    fun getColorValue(): Int {
        try {
            return Color.parseColor("#" + color!!)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return 0
        }
    }

}

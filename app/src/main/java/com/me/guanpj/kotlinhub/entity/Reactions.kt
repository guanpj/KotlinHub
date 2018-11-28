package com.me.guanpj.kotlinhub.entity

import com.google.gson.annotations.SerializedName
import com.me.guanpj.kotlinhub.util.anno.PoKo

@PoKo
class Reactions(@SerializedName("total_count") var totalCount: Int,
                @SerializedName("+1") var plusOne: Int,
                @SerializedName("-1") var minusOne: Int,
                var laugh: Int,
                var hooray: Int,
                var confused: Int,
                var heart: Int) {
}

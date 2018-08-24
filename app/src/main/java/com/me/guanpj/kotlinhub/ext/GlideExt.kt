package com.me.guanpj.kotlinhub.ext

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

fun CircleImageView.loadWithGlide(url: String, requestOptions: RequestOptions = RequestOptions()){
    Glide.with(this.context)
            .load(url)
            .apply(requestOptions)
            .into(this)
}
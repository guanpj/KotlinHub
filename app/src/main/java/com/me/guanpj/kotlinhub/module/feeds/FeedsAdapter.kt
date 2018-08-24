package com.me.guanpj.kotlinhub.module.feeds

import com.chad.library.adapter.base.BaseViewHolder
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.entity.Event
import com.me.guanpj.kotlinhub.ext.loadWithGlide
import com.me.guanpj.kotlinhub.module.list.CommonListAdapter
import de.hdodenhof.circleimageview.CircleImageView

class FeedsAdapter : CommonListAdapter<Event>(R.layout.item_feeds) {
    override fun convert(holder: BaseViewHolder, item: Event) {
        holder.setText(R.id.user_name, item.actor.login)
        holder.getView<CircleImageView>(R.id.user_avatar).loadWithGlide(item.actor.avatar_url)
    }
}
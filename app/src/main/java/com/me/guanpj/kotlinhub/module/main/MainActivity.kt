package com.me.guanpj.kotlinhub.module.main

import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }
        nav_view_start.setCheckedItem(R.id.mainView)
    }

    override fun initDataAndEvent() {
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {}
        }
        return super.onOptionsItemSelected(item)
    }*/
}

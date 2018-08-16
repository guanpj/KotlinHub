package com.me.guanpj.kotlinhub.module.login

import android.os.Bundle
import android.widget.EditText
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.activity.BaseMvpActivity
import com.me.guanpj.kotlinhub.ext.otherwise
import com.me.guanpj.kotlinhub.ext.yes
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_simple.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        signInButton.onClick {
            presenter.checkUserName(username.text.toString())
                    .yes {
                        presenter.checkPasswd(password.text.toString())
                                .yes {
                                    presenter.doLogin(username.text.toString(), password.text.toString())
                                }
                                .otherwise {
                                    showTips(password, "密码不合法")
                                }
                    }
                    .otherwise {
                        showTips(username, "用户名不合法")
                    }
        }
    }

    override fun initView() {
    }

    override fun initDataAndEvent() {

    }

    override fun onLoginError(e: Throwable){
        e.printStackTrace()
        toast("登录失败")
    }

    override fun onLoginSuccess(){
        toast("登录成功")
    }

    private fun showTips(view: EditText, tips: String){
        view.requestFocus()
        view.error = tips
    }
}
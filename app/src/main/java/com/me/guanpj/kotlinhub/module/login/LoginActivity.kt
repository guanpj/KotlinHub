package com.me.guanpj.kotlinhub.module.login

import android.os.Bundle
import android.widget.EditText
import com.me.guanpj.kotlinhub.R
import com.me.guanpj.kotlinhub.base.activity.BaseMvpActivity
import com.me.guanpj.kotlinhub.ext.otherwise
import com.me.guanpj.kotlinhub.ext.yes
import com.me.guanpj.kotlinhub.module.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInButton.onClick {
            presenter.checkUserName(user_name_et.text.toString())
                    .yes {
                        presenter.checkPasswd(password_et.text.toString())
                                .yes {
                                    presenter.doLogin(user_name_et.text.toString(), password_et.text.toString())
                                }
                                .otherwise {
                                    showTips(password_et, "密码不合法")
                                }
                    }
                    .otherwise {
                        showTips(user_name_et, "用户名不合法")
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
        jumpToActivity(MainActivity::class)
    }

    private fun showTips(view: EditText, tips: String){
        view.requestFocus()
        view.error = tips
    }
}
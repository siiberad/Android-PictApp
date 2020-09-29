package com.siiberad.pict.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.siiberad.pict.R
import com.siiberad.pict.ui.viewmodel.LoginViewModel
import com.siiberad.pict.utils.Util
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        fun show(source: Activity) {
            source.startActivity(Intent(source, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
        }
    }

    lateinit var vm: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)
        supportActionBar?.hide()
        btn_login.setOnClickListener {
            if (isValid()) {
                vm.getData(txt_email.text.toString())
                MainActivity.show(this)
            }
        }
    }

    private fun isValid(): Boolean {
        val ret = true

        if (txt_email.text?.isEmpty()!!) {
            txt_email.error = "Email Harus Diisi"
            return false
        }

        if (!Util.isEmailValid(txt_email.text.toString())) {
            txt_email.error = "Harus Email"
            return false
        }

        if (txt_password.text?.isEmpty()!!) {
            txt_password.error = "Password Harus Diisi"
            return false
        }
        return ret
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
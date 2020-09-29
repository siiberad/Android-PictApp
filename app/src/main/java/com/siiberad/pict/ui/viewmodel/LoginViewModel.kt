package com.siiberad.pict.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class LoginViewModel(app: Application) : BaseViewModel(app) {

    val s: SharedPreferences = app.getSharedPreferences("USER", Context.MODE_PRIVATE)

    fun getData(email: String) {
        val editor: SharedPreferences.Editor = s.edit()
        editor.putString("name", "Rakka Purnomo")
        editor.putString("email", email)
        editor.putString("gender", "Male")
        editor.apply()
    }

}
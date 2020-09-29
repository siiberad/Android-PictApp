package com.siiberad.pict.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.siiberad.pict.model.PictDatabase
import com.siiberad.pict.model.PictModel
import com.siiberad.pict.utils.Consta.DEFAULT_VALUE
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : BaseViewModel(app) {
    val s: SharedPreferences = app.getSharedPreferences("USER", Context.MODE_PRIVATE)
    val name = s.getString("name", DEFAULT_VALUE)
    val email = s.getString("email", DEFAULT_VALUE)
    val gender = s.getString("gender", DEFAULT_VALUE)

    val sourcesItem = MutableLiveData<List<PictModel>>()
    fun fetch() {
        launch {
            val data = PictDatabase(getApplication()).pictDao().getAllPicts()
            sourcesItem.postValue(data)
        }
    }

    fun logout() {
        s.edit().clear().apply()
    }

}
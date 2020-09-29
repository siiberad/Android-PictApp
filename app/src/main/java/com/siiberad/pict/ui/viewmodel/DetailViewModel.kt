package com.siiberad.pict.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.siiberad.pict.model.PictDatabase
import kotlinx.coroutines.launch

class DetailViewModel(app: Application) : BaseViewModel(app) {
    val listImage = MutableLiveData<List<String?>>()
    fun fetch(id: Int) {
        launch {
            val data = PictDatabase(getApplication()).pictDao().getPict(id)
            val list = listOf(data.url?.url1, data.url?.url2, data.url?.url3)
            listImage.value = list
        }
    }
}
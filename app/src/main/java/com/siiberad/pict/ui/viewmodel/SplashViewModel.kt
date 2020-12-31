package com.siiberad.pict.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.siiberad.pict.model.PictDatabase
import com.siiberad.pict.model.PictModel
import com.siiberad.pict.model.Result
import com.siiberad.pict.services.PictService
import com.siiberad.pict.utils.Consta.DEFAULT_VALUE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SplashViewModel(app: Application) : BaseViewModel(app) {
    val s: SharedPreferences = app.getSharedPreferences("USER", Context.MODE_PRIVATE)
    val email = s.getString("email", DEFAULT_VALUE)

    fun loggedIn(): Boolean {
        return email?.isNotEmpty()!!
    }

    private val pictService = PictService()
    private val disposable = CompositeDisposable()
    var action = MutableLiveData<Result>()

    fun getSources() = fetchSource()
    private fun fetchSource() {
        doAction(Result.InProgress)
        disposable.add(
            pictService.getSources()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PictModel>>() {
                    override fun onSuccess(t: List<PictModel>) {
                        resourseRecieved(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("d", e.message.toString())
                        doAction(Result.Failure(e.message.toString()))
                    }
                })
        )
    }

    private fun resourseRecieved(d: List<PictModel>) {
        launch {
            val dao = PictDatabase(getApplication()).pictDao()
            dao.deleteAll()
            val result = dao.insertAll(*d.toTypedArray())
            var i = 0
            while (i < d.size) {
                d[i].id = result[i].toInt()
                ++i
            }
            doAction(Result.Success(d))
        }
    }

    fun doAction(result: Result){
        action.value = result
    }
}
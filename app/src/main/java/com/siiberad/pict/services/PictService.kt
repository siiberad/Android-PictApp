package com.siiberad.pict.services

import com.siiberad.pict.BuildConfig
import com.siiberad.pict.model.PictModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PictService {
    private val api = Retrofit.Builder()
        .baseUrl(BuildConfig.BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PictApi::class.java)

    fun getSources(): Single<List<PictModel>> {
        return api.getSources()
    }

}
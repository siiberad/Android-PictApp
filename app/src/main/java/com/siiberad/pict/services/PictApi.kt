package com.siiberad.pict.services

import com.siiberad.pict.BuildConfig
import com.siiberad.pict.model.PictModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface PictApi {

    @Headers("secret-key:" + BuildConfig.SECRET_KEY)
    @GET("5f71e998302a837e956f84df")
    fun getSources(): Single<List<PictModel>>

}
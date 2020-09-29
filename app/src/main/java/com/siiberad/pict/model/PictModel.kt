package com.siiberad.pict.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PictModel(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    @SerializedName("title")
    val title: String? = null,
    @Embedded
    @SerializedName("url")
    val url: Url? = null
)

data class Url(
    @SerializedName("url3")
    val url3: String? = null,
    @SerializedName("url1")
    val url1: String? = null,
    @SerializedName("url2")
    val url2: String? = null
)


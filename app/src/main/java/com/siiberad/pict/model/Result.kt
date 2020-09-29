package com.siiberad.pict.model

sealed class Result {
    data class Success(val data: List<PictModel>) : Result()
    data class Failure(val exception: String) : Result()
    object InProgress : Result()
}
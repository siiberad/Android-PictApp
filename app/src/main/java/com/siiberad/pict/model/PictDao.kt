package com.siiberad.pict.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PictDao {
    @Insert
    suspend fun insertAll(vararg pict: PictModel): List<Long>

    @Query("SELECT * FROM pictmodel")
    suspend fun getAllPicts(): List<PictModel>

    @Query("SELECT * FROM pictmodel WHERE id = :id")
    suspend fun getPict(id: Int): PictModel

    @Query("DELETE FROM pictmodel")
    suspend fun deleteAll()

}
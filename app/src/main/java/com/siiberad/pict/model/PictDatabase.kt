package com.siiberad.pict.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PictModel::class], version = 1)
abstract class PictDatabase : RoomDatabase() {
    abstract fun pictDao(): PictDao

    companion object {
        @Volatile
        private var instance: PictDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PictDatabase::class.java,
            "pictdb"
        ).build()
    }
}
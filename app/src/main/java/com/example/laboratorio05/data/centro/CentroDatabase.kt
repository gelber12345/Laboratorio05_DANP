package com.example.laboratorio05.data.centro

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Centro::class], version = 1, exportSchema = false)

abstract class CentroDatabase : RoomDatabase() {

    abstract fun centroDao(): CentroDao

    companion object {
        @Volatile
        private var INSTANCE: CentroDatabase? = null

        fun getDatabase(context: Context): CentroDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    CentroDatabase::class.java, "jetpack")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
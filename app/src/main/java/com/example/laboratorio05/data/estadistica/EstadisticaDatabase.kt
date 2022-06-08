package com.example.laboratorio05.data.estadistica

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Estadistica::class], version = 2)
abstract class EstadisticaDatabase : RoomDatabase() {

    abstract fun estadisticaDao(): EstadisticaDao

    companion object {
        @Volatile
        private var INSTANCE: EstadisticaDatabase? = null

        fun getDatabase(context: Context): EstadisticaDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EstadisticaDatabase::class.java, "estadistica"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
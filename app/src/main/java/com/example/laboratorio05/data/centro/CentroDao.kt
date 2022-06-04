package com.example.laboratorio05.data.centro

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CentroDao {
    @Query("SELECT * FROM centro")
    fun getAllDataCentro(): LiveData<List<Centro>>

    @Query("SELECT * FROM centro WHERE latitud LIKE :search " +
            "or longitud LIKE :search " +
            "or departamento LIKE :search " +
            "or horario LIKE :search " +
            "or direccion LIKE :search ")
    fun findCentro(search: String):List<Centro>


    @Query("SELECT * FROM centro WHERE id = :id")
    fun findOneCentro(id: Int): Centro

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCentro(centro: Centro)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCentro(entity: Centro)

    @Query("DELETE FROM centro where id = :id")
    suspend fun deleteCentroById(id: Int)

    @Query("DELETE FROM centro")
    suspend fun deleteAllCentro()
}
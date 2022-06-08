package com.example.laboratorio05.data.estadistica

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EstadisticaDao {
    @Query("SELECT * FROM estadistica")
    fun getAllDataEstadistica(): LiveData<List<Estadistica>>

    @Query(
        "SELECT * FROM estadistica WHERE distrito LIKE :search " +
                "or positivosVivos LIKE :search " +
                "or positivosDefuncion LIKE :search " +
                "or negativo LIKE :search " +
                "or pendientes LIKE :search "
    )
    fun findEstadistica(search: String): List<Estadistica>


    @Query("SELECT * FROM estadistica WHERE id = :id")
    fun findOneEstadistica(id: Int): Estadistica

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEstadistica(estadistica: Estadistica)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEstadistica(entity: Estadistica)

    @Query("DELETE FROM estadistica where id = :id")
    suspend fun deleteEstadisticaById(id: Int)

    @Query("DELETE FROM estadistica")
    suspend fun deleteAllEstadistica()
}
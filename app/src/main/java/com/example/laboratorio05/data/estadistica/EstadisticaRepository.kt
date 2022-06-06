package com.example.laboratorio05.data.estadistica

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class EstadisticaRepository(application: Application) {
    private var estadisticaDao: EstadisticaDao
    val searchResults = MutableLiveData<List<Estadistica>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        val database = EstadisticaDatabase.getDatabase(application)
        estadisticaDao = database.estadisticaDao()
    }


    val readAllEstadistica: LiveData<List<Estadistica>> = estadisticaDao.getAllDataEstadistica()


    suspend fun insertEstadistica(estadistica: Estadistica) {
        estadisticaDao.insertEstadistica(estadistica)
    }

    suspend fun updateEstadistica(estadistica: Estadistica) {
        estadisticaDao.updateEstadistica(estadistica)
    }
    suspend fun deleteEstadisticaById(id: Int) {
        estadisticaDao.deleteEstadisticaById(id)
    }

    suspend fun deleteAllEstadistica() {
        estadisticaDao.deleteAllEstadistica()
    }

    fun findEstadistica(text: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(text).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Estadistica>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async estadisticaDao.findEstadistica(name)
        }
}
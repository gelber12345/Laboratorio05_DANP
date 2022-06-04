package com.example.laboratorio05.data.centro

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*


class CentroRepository(application: Application) {
    private var centroDao: CentroDao
    val searchResults = MutableLiveData<List<Centro>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        val database = CentroDatabase.getDatabase(application)
        centroDao = database.centroDao()
    }


    val readAllCentros: LiveData<List<Centro>> = centroDao.getAllDataCentro()


    suspend fun insertCentro(centro: Centro) {
        centroDao.insertCentro(centro)
    }

    suspend fun updateCentro(centro: Centro) {
        centroDao.updateCentro(centro)
    }
    suspend fun deleteCentroById(id: Int) {
        centroDao.deleteCentroById(id)
    }

    suspend fun deleteAllCentro() {
        centroDao.deleteAllCentro()
    }

    fun findCentro(text: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(text).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Centro>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async centroDao.findCentro(name)
        }
}
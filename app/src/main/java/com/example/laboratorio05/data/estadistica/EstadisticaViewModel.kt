package com.example.laboratorio05.data.estadistica

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EstadisticaViewModel(appObj: Application) : AndroidViewModel(appObj) {
    private val estadisticaRepository: EstadisticaRepository = EstadisticaRepository(appObj)

    val searchResults: MutableLiveData<List<Estadistica>> = estadisticaRepository.searchResults

    fun fetchAllEstadistica(): LiveData<List<Estadistica>> {
        return estadisticaRepository.readAllEstadistica
    }


    fun insertEstadistica(estadistica: Estadistica) {
        viewModelScope.launch {
            estadisticaRepository.insertEstadistica(estadistica = estadistica)
        }

    }
    fun findEstadistica(text: String) {
        viewModelScope.launch {
            estadisticaRepository.findEstadistica(text)
        }

    }
    fun updateEstadistica(estadistica: Estadistica) {
        viewModelScope.launch {
            estadisticaRepository.updateEstadistica(estadistica = estadistica)
        }

    }
    fun deleteEstadisticaById(id: Int) {
        viewModelScope.launch {
            estadisticaRepository.deleteEstadisticaById(id)
        }
    }
    fun deleteAllEstadistica() {
        viewModelScope.launch {
            estadisticaRepository.deleteAllEstadistica()
        }
    }

}
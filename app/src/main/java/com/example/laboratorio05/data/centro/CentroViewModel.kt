package com.example.laboratorio05.data.centro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CentroViewModel (appObj: Application) : AndroidViewModel(appObj) {

    private val centroRepository: CentroRepository = CentroRepository(appObj)

    val searchResults: MutableLiveData<List<Centro>> = centroRepository.searchResults

    fun fetchAllCentro(): LiveData<List<Centro>> {
        return centroRepository.readAllCentros
    }


    fun insertCentro(centro: Centro) {
        viewModelScope.launch {
            centroRepository.insertCentro(centro = centro)
        }

    }
    fun findCentro(text: String) {
        viewModelScope.launch {
            centroRepository.findCentro(text)
        }

    }
    fun updateCentro(centro: Centro) {
        viewModelScope.launch {
            centroRepository.updateCentro(centro = centro)
        }

    }
    fun deleteCentroById(id: Int) {
        viewModelScope.launch {
            centroRepository.deleteCentroById(id)
        }
    }
    fun deleteAllCentro() {
        viewModelScope.launch {
            centroRepository.deleteAllCentro()
        }
    }

}
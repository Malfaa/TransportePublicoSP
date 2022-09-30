package com.malfaa.transportepublicosp.informativo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.transportepublicosp.network.models.LinhaDir
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InformativoViewModel(private val repositorio: Repositorio) : ViewModel() {
    val linhas = repositorio.getLinhas

    fun pesquisarLinha(linha:String){
        viewModelScope.launch {
            try {
                repositorio.refreshLinhas(linha)
            }catch (e: Exception){
                Log.e("IVM 16", e.toString())
            }
        }
    }

    fun apagarDadosVencidos(){
        viewModelScope.launch {
            repositorio.removerLinhas().apply {
                Log.d("IVM 29 - Database","Apagado")
            }
        }
    }
}
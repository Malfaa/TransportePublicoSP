package com.malfaa.transportepublicosp.informativo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.launch

class InformativoViewModel(private val repositorio: Repositorio) : ViewModel() {

    val linha = MutableLiveData<String>()
    val linhas = repositorio.getLinhas

    fun pesquisarLinha(){
        viewModelScope.launch {
            try {
                repositorio.refreshLinhas(linha.value!!)
            }catch (e: Exception){
                //Result.failure<String>(e.cause!!)
            }
        }
    }

    fun apagarDadosVencidos(){
        viewModelScope.launch {
            repositorio.removerLinhas()
        }
    }
}


// Codog linha -> edittext, logo informação dada
// Codigo de parada tem que arranjar de algum jeito via localização por aproximação/
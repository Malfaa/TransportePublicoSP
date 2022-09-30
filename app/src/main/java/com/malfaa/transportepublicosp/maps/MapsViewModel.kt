package com.malfaa.transportepublicosp.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.transportepublicosp.network.models.Onibus
import com.malfaa.transportepublicosp.repository.IRepositorio
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.launch

class MapsViewModel (private val repositorio: Repositorio) :ViewModel(){

    val args = MutableLiveData<MapsFragmentArgs>()

    val onibus = repositorio.getOnibusPosicao


//    lateinit var filtroAplicado : List<Onibus> //Onibus

    //    fun filtroDeLinha(c: String, sl: Int) {
//        //após filtrar, mandar para o fragment colocar as marcações e as linhas das ruas no local certo
//        for(onibus in onibus.value!!){
//            if (onibus.c == c && onibus.sl == sl){
//                filtroAplicado//filtroAplicado = onibus
//            }
//        }
//    }

    init {
        refreshOnibus()
    }

    fun refreshOnibus(){
        viewModelScope.launch {
            repositorio.refreshPosicao(args.value?.linha!!.cl) //cogido linha //filtroDeLinha("${args.value!!.linha.lt}${args.value!!.linha.tl}", args.value!!.linha.sl)
        }
    }

    fun deletarDBDesatualizado(){
        viewModelScope.launch {
            repositorio.removerOnibus()
        }
    }

    fun acaoDeRefresh(){
        deletarDBDesatualizado()
        refreshOnibus()
    }
}
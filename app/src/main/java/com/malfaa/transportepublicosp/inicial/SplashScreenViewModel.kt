package com.malfaa.transportepublicosp.inicial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.transportepublicosp.network.SPTransHttpClient.Companion.API_KEY
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val repositorio: Repositorio) : ViewModel() {

    fun autentica(){
        viewModelScope.launch {
            repositorio.autenticacao(API_KEY) //resgata o token da api
        }
    }
}
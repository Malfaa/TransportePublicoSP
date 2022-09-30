package com.malfaa.transportepublicosp.inicial

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val repositorio: Repositorio) : ViewModel() {

    private val _resultado = MutableLiveData<Boolean>()

    val resultado :MutableLiveData<Boolean>
        get() = _resultado

    init {
        autenticacao()
    }

    fun autenticacao(){
        viewModelScope.launch {
            _resultado.value = repositorio.autenticacao()
        }
    }
}
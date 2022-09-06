package com.malfaa.transportepublicosp.informativo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malfaa.transportepublicosp.local.entidade.Onibus
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.launch

class InformativoViewModel(private val repositorio: Repositorio) : ViewModel() {

    val linha = MutableLiveData<String>()

    fun refresh(){
        viewModelScope.launch {
            //repositorio.onibusPrevisao()
        }
    }

}

//resultado da pesquisa feita, são, por exemplo, 971-A -> terá 2 itens no adapter.
// Acho que pode aparecer o resultado dos dois... ou isso criará confusão no usuário?
// Dependendo de qual for clicado, será gravado o item e disponibilizado todas as infos.
// E TALVEZ COM ISSO FAÇA UMA PESQUISA PRA TER NO MAPA, DAÍ NEM PRECISA REFAZER A PESQUISA.
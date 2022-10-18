package com.malfaa.transportepublicosp

import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados
import com.malfaa.transportepublicosp.repository.IRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FakeDataSource {

    private var onibus = mutableListOf<VeiculosLocalizados>()


    suspend fun onibus(): Boolean {
        return withContext(Dispatchers.IO) {
            onibus.size > 0
        }
    }

    suspend fun refreshLinhas(item: VeiculosLocalizados) {
        withContext(Dispatchers.IO){
            onibus.add(item)
        }
    }
}
package com.malfaa.transportepublicosp.repository

import com.malfaa.transportepublicosp.network.SPTransApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repositorio(private val api: SPTransApi) : IRepositorio {
    //fetch data da Api

    override suspend fun onibusPrevisao(
        codigoParada: Int,
        codigoLinha: Int
    ){
        withContext(Dispatchers.IO){
            //val response = api.retrofitService.getPrevisao(codigoParada,codigoLinha)


        }
    }
}

//colocar adapter de json pra room
//fazer chamada a cada 20 segundos? e ter um refresher pra chamar de novo?



package com.malfaa.transportepublicosp.repository

import com.malfaa.transportepublicosp.data.Result
import com.malfaa.transportepublicosp.local.linha.LinhaDatabase
import com.malfaa.transportepublicosp.local.onibus.OnibusDatabase
import com.malfaa.transportepublicosp.network.SPTransApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repositorio(private val api: SPTransApi, private val linhaDB: LinhaDatabase, private val onibusDB: OnibusDatabase) : IRepositorio {
    //fetch data da Api

    //Autenticação
    override suspend fun autenticacao(token: String):Boolean {
        return withContext(Dispatchers.IO){
            when(api.retrofitService.autentica(token)){
                true -> return@withContext true
                false -> return@withContext false
            }
        }
    }

    //LINHAS
    val getLinhas = linhaDB.dao.getLinhas()

    override suspend fun refreshLinhas(termosBusca: String) {
        withContext(Dispatchers.IO){
            val response = api.retrofitService.getLinha(termosBusca)
            linhaDB.dao.adicionaLinha(response)
        }
    }

    override suspend fun removerLinhas() {
        withContext(Dispatchers.IO){
            linhaDB.dao.deletaLinhas()
        }
    }

    //ONIBUS
    val getOnibusPosicao = onibusDB.dao.getOnibusPrevisao()

    override suspend fun refreshPosicao(){
        withContext(Dispatchers.IO){
            val response = api.retrofitService.getPosicaoTempoReal()
            onibusDB.dao.addOnibus(response)
        }
    }

    override suspend fun refreshPosicao(codigoLinha: Int){
        withContext(Dispatchers.IO){
            val response = api.retrofitService.getPosicaoLinha(codigoLinha)
            onibusDB.dao.addOnibus(response)
        }
    }

    override suspend fun removerOnibus() {
        withContext(Dispatchers.IO){
            onibusDB.dao.deletaListaPassada()
        }
    }
}
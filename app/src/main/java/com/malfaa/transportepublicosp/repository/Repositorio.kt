package com.malfaa.transportepublicosp.repository

import android.util.Log
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.local.linha.LinhaDatabase
import com.malfaa.transportepublicosp.local.onibus.OnibusDatabase
import com.malfaa.transportepublicosp.network.SPTransApi
import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repositorio(private val api: SPTransApi, private val linhaDB: LinhaDatabase, private val onibusDB: OnibusDatabase) : IRepositorio {
    //fetch data da Api
    companion object{
        lateinit var COOKIE : String
    }

    //Autenticação
    override suspend fun autenticacao(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                api.retrofitService.autentica(Constante.API_KEY).apply {
                    Log.d("Verifica", headers().toString())
                    COOKIE = headers().get("Set-Cookie").toString()
                }.isSuccessful
            }catch (e: Exception) {
                Log.e("Auth CATCH", e.toString())
                Log.e("Auth CATCH", "Failed")
                false
            }
        }
    }

    //LINHAS
    val getLinhas = linhaDB.ldao.getLinhas()

    override suspend fun refreshLinhas(termosBusca: String) {
        withContext(Dispatchers.IO){
            val response = api.retrofitService.getLinha(COOKIE ,termosBusca)
            linhaDB.ldao.adicionaLinha(response)
        }
    }

    override suspend fun removerLinhas() {
        withContext(Dispatchers.IO){
            linhaDB.ldao.deletaLinhas()
        }
    }

    //ONIBUS
    override suspend fun onibus():List<VeiculosLocalizados> {
        return withContext(Dispatchers.IO) {
            onibusDB.dao.getLocalizacaoOnibus()
        }
    }

    override suspend fun refreshPosicao(codigoLinha: Int){
        withContext(Dispatchers.IO){
            val response = api.retrofitService.getPosicaoLinha(COOKIE, codigoLinha).apply {
                Log.d("Localizacao Inserida", this.vl.toString())
            }
            onibusDB.dao.addOnibus(response.vl)
        }
    }

    override suspend fun removerOnibus() {
        withContext(Dispatchers.IO){
            onibusDB.dao.deletaListaPassada()
        }
    }
}
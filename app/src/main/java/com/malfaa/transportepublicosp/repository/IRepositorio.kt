package com.malfaa.transportepublicosp.repository

import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados

interface IRepositorio {
    //Autenticacao
    suspend fun autenticacao(): Boolean

    //Linhas
    suspend fun refreshLinhas(termosBusca: String)
    suspend fun removerLinhas()

    //Onibus
    suspend fun onibus(): List<VeiculosLocalizados>
    suspend fun refreshPosicao(codigoLinha: Int)
    suspend fun removerOnibus()
}
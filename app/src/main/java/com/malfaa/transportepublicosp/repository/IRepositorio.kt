package com.malfaa.transportepublicosp.repository

interface IRepositorio {
    //Autenticacao
    suspend fun autenticacao(token: String): Boolean

    //Linhas
    suspend fun refreshLinhas(termosBusca: String)
    suspend fun removerLinhas()

    //Onibus

    suspend fun refreshPosicao()

    suspend fun refreshPosicao(codigoLinha: Int)

    suspend fun removerOnibus()
}
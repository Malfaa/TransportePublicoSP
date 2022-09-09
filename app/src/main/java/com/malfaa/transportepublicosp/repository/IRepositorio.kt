package com.malfaa.transportepublicosp.repository

interface IRepositorio {
    //Linhas
    suspend fun refreshLinhas(termosBusca: String)
    suspend fun removerLinhas()

    //Onibus

    suspend fun refreshPosicao()

    suspend fun removerOnibus()
}
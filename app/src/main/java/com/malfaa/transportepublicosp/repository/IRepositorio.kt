package com.malfaa.transportepublicosp.repository

interface IRepositorio {

    suspend fun onibusPrevisao(codigoParada: Int,codigoLinha: Int)


}
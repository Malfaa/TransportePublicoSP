package com.malfaa.transportepublicosp.network

import com.malfaa.transportepublicosp.network.models.LinhaDir
import com.malfaa.transportepublicosp.network.models.Onibus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

object SPTransApi{
    private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(SPTransHttpClient.getClient())
        .baseUrl(BASE_URL)
        .build()

    /*
     *  Documentation for the SPTrans Api
     *  https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api/
     *  http://api.olhovivo.sptrans.com.br/v2.1
     */

    val retrofitService: SPTransApiService by lazy {
        retrofit.create(SPTransApiService::class.java)
    }
}

interface SPTransApiService {
    //Autenticação
    @POST("Login/Autenticar")
    suspend fun autentica(
        @Query("token")
        token: String
    ): Response<String>

    //Buscar Linhas
    @GET("Linha/Buscar")
    suspend fun getLinha(
        @Header("Cookie")
        cookie: String,
        @Query("termosBusca")
        termosBusca: String
    ): List<LinhaDir>

    //Buscar Posição dos Veículos
    @GET("Posicao/Linha")
    suspend fun getPosicaoLinha(
        @Header("Cookie")
        cookie: String,
        @Query("codigoLinha")
        codigoLinha: Int
    ) : Onibus
}
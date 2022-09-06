package com.malfaa.transportepublicosp.network

import com.malfaa.transportepublicosp.network.models.Posicao
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

object SPTransApi{
    private const val BASE_URL = "https://api.olhovivo.sptrans.com.br/v2.1/"

    private val moshi = Moshi.Builder()
        .add(JsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(SPTransHttpClient.getClient())
        .baseUrl(BASE_URL)
        .build()

    /**
     *  Documentation for the SPTrans Api https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api/
     *  https://api.olhovivo.sptrans.com.br/v2.1
     */

    val retrofitService: SPTransApiService by lazy {
        retrofit.create(SPTransApiService::class.java)
    }
}
interface SPTransApiService {
    @POST("Login/Autenticar?token=")
    suspend fun autentica(

    ): Result<Boolean>

    //Autenticação
    @GET("Posicao")
    suspend fun getPosicaoTempoReal() : Posicao //verificar se precisa de parâmetro para retornar a info necessária, no caso o busão desejado

    @GET("Previsao")//?codigoParada={codigoParada}&codigoLinha={codigoLinha}
    suspend fun getPrevisao(
        @Query("codigoParada")
        codigoParada: Int,
        @Query("codigoLinha")
        codigoLinha: Int
    ) //: dataclass

//https://pt.stackoverflow.com/questions/263011/erro-na-autentica%C3%A7%C3%A3o-da-api-restful-com-curl-php
}
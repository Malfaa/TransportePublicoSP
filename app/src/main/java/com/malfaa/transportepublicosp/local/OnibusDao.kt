package com.malfaa.transportepublicosp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.local.entidade.Onibus

@Dao
interface OnibusDao {

    @Query("SELECT * FROM ${Constante.PREVISAO_TABLE_NAME}")
    fun getOnibusPrevisao(): LiveData<List<Onibus>>

//    @Query("SELECT * FROM ${Constante.TABLE_NAME}")
//    fun getOnibusRealTime(): LiveData<List<Onibus>>

    @Insert
    suspend fun addOnibus(item: Onibus)

    @Query("DROP TABLE ${Constante.PREVISAO_TABLE_NAME}")//clear db
    suspend fun deletaListaPassada()

}

//Talvez fazer dois databases, que cada um faz uma chamada e grava no db, por exemplo um é de previsao
// e o outro é de tempo real
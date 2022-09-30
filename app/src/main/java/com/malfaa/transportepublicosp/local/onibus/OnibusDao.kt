package com.malfaa.transportepublicosp.local.onibus

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.network.models.Onibus

@Dao
interface OnibusDao {

    @Query("SELECT * FROM ${Constante.PREVISAO_TABLE_NAME}")
    fun getOnibusPrevisao(): LiveData<List<Onibus>>

    @Insert
    suspend fun addOnibus(item: List<Onibus>)

    @Query("DELETE FROM ${Constante.PREVISAO_TABLE_NAME}")//clear db
    suspend fun deletaListaPassada()

}
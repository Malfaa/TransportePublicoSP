package com.malfaa.transportepublicosp.local.onibus

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.network.models.Onibus
import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados

@Dao
interface OnibusDao {

//    @Query("SELECT * FROM ${Constante.ONIBUS_TABLE_NAME}")
//    fun getOnibusPrevisao(): LiveData<Onibus>

    @Query("SELECT * FROM ${Constante.ONIBUS_TABLE_NAME}")
    fun getLocalizacaoOnibus(): LiveData<List<VeiculosLocalizados>>

    @Insert
    suspend fun addOnibus(item: List<VeiculosLocalizados>)

    @Query("DELETE FROM ${Constante.ONIBUS_TABLE_NAME}")//clear db
    suspend fun deletaListaPassada()
}
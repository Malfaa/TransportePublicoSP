package com.malfaa.transportepublicosp.local.linha

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.network.models.LinhaDir

@Dao
interface LinhaDao {
    @Query("SELECT * FROM ${Constante.LINHA_TABLE_NAME}")
    fun getLinhas(): LiveData<List<LinhaDir>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adicionaLinha(linha: List<LinhaDir>)

    @Query("DELETE FROM ${Constante.LINHA_TABLE_NAME}")//clear db
    suspend fun deletaLinhas()
}
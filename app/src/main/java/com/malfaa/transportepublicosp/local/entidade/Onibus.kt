package com.malfaa.transportepublicosp.local.entidade

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malfaa.transportepublicosp.Constante
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constante.PREVISAO_TABLE_NAME)
@Parcelize
data class Onibus(
    @PrimaryKey
    val id: Int
): Parcelable
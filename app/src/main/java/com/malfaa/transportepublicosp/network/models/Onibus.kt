package com.malfaa.transportepublicosp.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malfaa.transportepublicosp.Constante
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

//@Entity(tableName = Constante.ONIBUS_TABLE_NAME)
@Parcelize
data class Onibus( //posicao
    @Json(name = "hr")
    val hr: String,  // Horário de referência da geração das informações
    @Json(name = "vs")
    val vl: List<VeiculosLocalizados>
): Parcelable
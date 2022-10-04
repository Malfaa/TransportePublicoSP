package com.malfaa.transportepublicosp.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malfaa.transportepublicosp.Constante
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constante.ONIBUS_TABLE_NAME)
@Parcelize
data class VeiculosLocalizados(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @Json(name = "p")
    val p : Int,  // Prefixo do veículo
    @Json(name = "a")
    val a : Boolean, // Indica se o veículo é (true) ou não (false) acessível para pessoas com deficiência
    @Json(name = "ta")
    val ta : String, // Indica o horário universal (UTC) em que a localização foi capturada. Essa informação está no padrão ISO 8601
    @Json(name = "py")
    val py: Double,  // Informação de latitude da localização do veículo
    @Json(name = "px")
    val px : Double   // Informação de longitude da localização do veículo
): Parcelable

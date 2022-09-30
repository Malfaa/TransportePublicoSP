package com.malfaa.transportepublicosp.network.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malfaa.transportepublicosp.Constante
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constante.LINHA_TABLE_NAME)
data class LinhaDir(
    @PrimaryKey
    @Json(name = "cl")
    val cl  : Int,     // Código identificador da linha. Este é um código identificador único de cada linha do sistema (por sentido de operação)
    @Json(name = "lc")
    val lc  : Boolean, // Indica se uma linha opera no modo circular (sem um terminal secundário)
    @Json(name = "lt")
    val lt  : String,  // Informa a primeira parte do letreiro numérico da linha
    @Json(name = "tl")
    val tl  : Int,     // Informa a segunda parte do letreiro numérico da linha, que indica se a linha opera nos modos: BASE (10), ATENDIMENTO (21, 23, 32, 41)
    @Json(name = "sl")
    val sl  : Int,     // Informa o sentido ao qual a linha atende, onde 1 significa Terminal Principal para Terminal Secundário e 2 para Terminal Secundário para Terminal Principal
    @Json(name = "tp")
    val tp  : String,  // Informa o letreiro descritivo da linha no sentido Terminal Principal para Terminal Secundário
    @Json(name = "ts")
    val ts  : String,  // Informa o letreiro descritivo da linha no sentido Terminal Secundário para Terminal Principal
):Parcelable

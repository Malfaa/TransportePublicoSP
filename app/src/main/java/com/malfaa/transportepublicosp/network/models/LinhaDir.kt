package com.malfaa.transportepublicosp.network.models

import android.os.Parcelable
import androidx.room.Entity
import com.malfaa.transportepublicosp.Constante
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constante.LINHA_TABLE_NAME)
@Parcelize
data class LinhaDir(
    val cl  : Int,     // Código identificador da linha. Este é um código identificador único de cada linha do sistema (por sentido de operação)
    val lc  : Boolean, // Indica se uma linha opera no modo circular (sem um terminal secundário)
    val lt  : String,  // Informa a primeira parte do letreiro numérico da linha
    val tl  : Int,     // Informa a segunda parte do letreiro numérico da linha, que indica se a linha opera nos modos: BASE (10), ATENDIMENTO (21, 23, 32, 41)
    val sl  : Int,     // Informa o sentido ao qual a linha atende, onde 1 significa Terminal Principal para Terminal Secundário e 2 para Terminal Secundário para Terminal Principal
    val tp  : String,  // Informa o letreiro descritivo da linha no sentido Terminal Principal para Terminal Secundário
    val ts  : String,  // Informa o letreiro descritivo da linha no sentido Terminal Secundário para Terminal Principal
):Parcelable

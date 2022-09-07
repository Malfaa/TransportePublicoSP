package com.malfaa.transportepublicosp.network.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malfaa.transportepublicosp.Constante
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constante.PREVISAO_TABLE_NAME)
@Parcelize
data class Onibus( //posicao
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hr  : String,  // Horário de referência da geração das informações  s
    val c   : String,  // Letreiro completo  s
    val cl  : Int,     // Código identificador da linha  s
    val cp  : Int,     // código identificador da parada   s
    val lt0 : String,  // Letreiro de destino da linha
    val qv  : Int,     // Quantidade de veículos localizados  s
    val a   : Boolean, // Indica se o veículo é (true) ou não (false) acessível para pessoas com deficiência
    val py  : Double,  // Informação de latitude da localização do veículo
    val px  : Double   // Informação de longitude da localização do veículo
): Parcelable//    val previsao: Previsao

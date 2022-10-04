package com.malfaa.transportepublicosp.utils

import androidx.room.TypeConverter
import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados

class Converters {
//    @TypeConverter
//    fun deListaParaVL(p : Int, a : Boolean, ta : String, py: Double, px: Double): VeiculosLocalizados{
//        return VeiculosLocalizados(0, p, a, ta, py, px)
//    }

    @TypeConverter
    fun prefixoParaLista(vl: VeiculosLocalizados): Int {
        return  vl.p
    }
    @TypeConverter
    fun acessivelParaLista(vl: VeiculosLocalizados):Boolean{
        return vl.a
    }
    @TypeConverter
    fun locCaptParaLista(vl: VeiculosLocalizados):String{
        return vl.ta
    }
    @TypeConverter
    fun posYParaLista(vl: VeiculosLocalizados):Double{
        return vl.py
    }
//    @TypeConverter
//    fun posXParaLista(vl: VeiculosLocalizados):Double{
//        return vl.px
//    }

    @TypeConverter
    fun deListaParaP(p: Int): VeiculosLocalizados {
        return VeiculosLocalizados(0, p, false, "", 0.0, 0.0)//VeiculosLocalizados(0, p, null, null, null, null)
    }
    @TypeConverter
    fun deListaParaA(a : Boolean): VeiculosLocalizados{
        return VeiculosLocalizados(0, 0, a, "", 0.0, 0.0)
    }
    @TypeConverter
    fun deListaParaTA(ta : String): VeiculosLocalizados{
        return VeiculosLocalizados(0, 0, false, ta, 0.0, 0.0)
    }
    @TypeConverter
    fun deListaParaPY(py: Double): VeiculosLocalizados{
        return VeiculosLocalizados(0, 0, false, "", py, 0.0)
    }
//    @TypeConverter
//    fun deListaParaPX(px: Double): VeiculosLocalizados{
//        return VeiculosLocalizados(0, 0, false, "", 0.0, px)
//    }
}


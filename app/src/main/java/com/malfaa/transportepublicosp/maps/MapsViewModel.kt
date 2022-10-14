package com.malfaa.transportepublicosp.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados
import com.malfaa.transportepublicosp.repository.Repositorio
import kotlinx.coroutines.launch
import java.io.InputStream

class MapsViewModel (private val repositorio: Repositorio): ViewModel(){

    val args = MutableLiveData<MapsFragmentArgs>()

    lateinit var onibus: List<VeiculosLocalizados>
    val testeSeAlteraAsPosicoes = MutableLiveData(true)
    lateinit var camera: LatLng
    lateinit var markersLargadaChegada: List<LatLng>

    fun refreshOnibus(){
        viewModelScope.launch {
            repositorio.removerOnibus()

            repositorio.refreshPosicao(args.value?.linha!!.cl)
            onibus = repositorio.onibus()
        }
    }

    fun fileLeituraTrips(context: Context, lt: String, tl: Int,sl: Int): String {
        val getAssets = context.assets
        val inputStream: InputStream = getAssets.open("trips.txt")

        val lineList = mutableListOf<String>()
        val valorNum = "$lt-$tl"

        inputStream.bufferedReader().forEachLine {
            val verificaLinha = it.contains(valorNum)
            if (verificaLinha){
                lineList.add(it)
            }
        }

        val retornaShapeId = "\"[0-9]{5}\"".toRegex()

        return when(sl){
            1 -> retornaShapeId.find(lineList[0])?.value.toString()
            2 -> retornaShapeId.find(lineList[1])?.value.toString()
            else -> ""
        }.apply {
            Log.d("Valores FLeituraTrips",this)
        }
    }

    fun retornaCoordenadasRota(context: Context,shape_id: String): MutableList<LatLng>{
        val getAssets = context.assets
        val inputStream: InputStream = getAssets.open("shapes.txt")
        val lineList = mutableListOf<String>()

        val coordenadasRegex = "-[0-9]{2}\\.[0-9]{6}".toRegex()

        inputStream.bufferedReader().forEachLine { it ->
            val verificaLinha = it.contains(shape_id)
            if (verificaLinha){
                lineList.add(coordenadasRegex.findAll(it).map{ it.value}.toList().toString() )
            }
        }// retorna em string [[-23.514351 , -43.14351], ...]

        val coordenadas = mutableListOf<LatLng>()

        for (i in lineList){
            coordenadas.add(
                LatLng(
                    i.substringAfter("[").substringBefore(", ").toDouble(),
                    i.substringAfter(", ").substringBefore("]").toDouble()
                )
            )
        }


        camera = moveCameraAteCentro(coordenadas.first(),coordenadas.last())
        markersLargadaChegada = chegadaLargada(coordenadas.first(),coordenadas.last())

        return coordenadas.apply {
            Log.d("Valores RCR",this.toString())
        }
    }

    fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    private fun moveCameraAteCentro(first: LatLng, last: LatLng): LatLng {
        return LatLng((first.latitude+last.latitude)/2, (first.longitude+last.longitude)/2)
    }
    fun chegadaLargada(first: LatLng, last: LatLng): List<LatLng> {
        return listOf(first,last)
    }

    fun deletarDBDesatualizado(){
        viewModelScope.launch {
            repositorio.removerOnibus()
        }
    }
}
package com.malfaa.transportepublicosp.informativo

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.malfaa.transportepublicosp.network.models.LinhaDir

@BindingAdapter("linhaUm")
fun linhaUm (textView: TextView, linha: LinhaDir){
    if(linha.sl == 1){
        textView.text = linha.ts
    }else{
        textView.text = linha.tp
    }
}

@BindingAdapter("linhaDois")
fun linhaDois (textView: TextView, linha: LinhaDir){
    if(linha.sl == 1){
        textView.text = linha.tp
    }else{
        textView.text = linha.ts
    }
}

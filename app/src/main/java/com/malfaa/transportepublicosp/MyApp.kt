package com.malfaa.transportepublicosp

import android.app.Application
import com.malfaa.transportepublicosp.informativo.InformativoViewModel
import com.malfaa.transportepublicosp.local.linha.LinhaDatabase
import com.malfaa.transportepublicosp.local.onibus.OnibusDatabase
import com.malfaa.transportepublicosp.network.SPTransApi
import com.malfaa.transportepublicosp.repository.Repositorio
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MyApp : Application(){
    //koin
    override fun onCreate() {
        super.onCreate()

        val meuModulo = module{

            single { OnibusDatabase.getInstance(this@MyApp) }
            single { LinhaDatabase.getInstance(this@MyApp) }
            single { Repositorio( get() as SPTransApi, get(), get()) }
            viewModel{ InformativoViewModel(get() as Repositorio) }

        }

        startKoin{
            androidContext(this@MyApp)
            modules(meuModulo)
        }
    }
}
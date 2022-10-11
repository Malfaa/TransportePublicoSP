package com.malfaa.transportepublicosp.local.onibus

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados
import com.malfaa.transportepublicosp.utils.Converters

@Database(entities = [VeiculosLocalizados::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class OnibusDatabase: RoomDatabase(){

    abstract val dao: OnibusDao

    companion object {
        @Volatile
        private var INSTANCE: OnibusDatabase? = null

        fun getInstance(context: Context): OnibusDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        OnibusDatabase::class.java,
                        Constante.ONIBUS_TABLE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}
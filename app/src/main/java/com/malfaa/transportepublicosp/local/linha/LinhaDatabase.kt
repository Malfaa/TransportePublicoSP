package com.malfaa.transportepublicosp.local.linha

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.malfaa.transportepublicosp.Constante
import com.malfaa.transportepublicosp.network.models.LinhaDir

@Database(entities = [LinhaDir::class], version = 1, exportSchema = false)
abstract class LinhaDatabase: RoomDatabase(){

    abstract val ldao: LinhaDao

    companion object {
        @Volatile
        private var INSTANCE: LinhaDatabase? = null

        fun getInstance(context: Context): LinhaDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LinhaDatabase::class.java,
                        Constante.LINHA_TABLE_NAME
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
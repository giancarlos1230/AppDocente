package net.larntech.appdocente

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Docente::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun DocenteDao(): DocenteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "app_db"
                    )
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
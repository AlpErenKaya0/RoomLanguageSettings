package com.alperen.roomonevariable.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Language::class], /*bu önemli, 1 artırman gerekebilir*/version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun languageDao(): LanguageDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase?=null
        private val lock = Any()
        operator fun invoke(context: Context) = INSTANCE?: synchronized(lock){
            INSTANCE ?:makeDatabase(context).also{
                INSTANCE = it
            }
        }
        private fun makeDatabase (context: Context) = Room.databaseBuilder(
            context.applicationContext, UserDatabase::class.java,"user_database"
        ).build()
    }
}

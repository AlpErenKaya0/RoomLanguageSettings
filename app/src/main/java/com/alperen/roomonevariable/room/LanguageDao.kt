package com.alperen.roomonevariable.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LanguageDao {
    @Query(value="SELECT * FROM language_table ORDER BY id ASC ")
    fun readAllData(): LiveData<List<Language>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLanguage(language: Language)

}


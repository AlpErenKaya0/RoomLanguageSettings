package com.alperen.roomonevariable.room

import androidx.lifecycle.LiveData

class LanguageRepository(private val languageDao: LanguageDao) {
    val readAllData : LiveData<List<Language>> = languageDao.readAllData()
    suspend fun addLanguage(language: Language) {
        languageDao.addLanguage(language)
    }

}
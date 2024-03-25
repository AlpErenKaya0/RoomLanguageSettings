package com.alperen.roomonevariable.room.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alperen.roomonevariable.room.Language
import com.alperen.roomonevariable.room.LanguageDao
import com.alperen.roomonevariable.room.LanguageRepository
import com.alperen.roomonevariable.room.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LanguageViewModel(application: Application):AndroidViewModel(application) {
   private val repository: LanguageRepository
   val readAllData:LiveData<List<Language>>
    init {
        val languageDao = UserDatabase.invoke(application).languageDao()
        repository = LanguageRepository(languageDao)
        readAllData = languageDao.readAllData()
    }
    fun addLanguage(language:Language) {
        viewModelScope.launch(Dispatchers.IO) {
            //yapılacak işlemlerin fonksiyonlarını kullandık
            repository.addLanguage(language)
        }
    }

}

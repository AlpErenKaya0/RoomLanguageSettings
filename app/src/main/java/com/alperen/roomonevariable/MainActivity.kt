package com.alperen.roomonevariable

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alperen.roomonevariable.databinding.ActivityMainBinding
import com.alperen.roomonevariable.room.Language
import com.alperen.roomonevariable.room.ViewModel.LanguageViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selectedLanguage = 0 // 0: Turkish, 1: English, 2: German
    private lateinit var languageViewModel: LanguageViewModel
    private val language = mutableListOf<Language>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        languageViewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
            val language = when (radioButton) {
                binding.radioButton -> {
                    selectedLanguage = 1
                    "Turkish"
                }

                binding.radioButton2 -> {
                    selectedLanguage = 2
                    "English"
                }

                binding.radioButton3 -> {
                    selectedLanguage = 3
                    "German"
                }

                else -> ""
            }
            Toast.makeText(this, "Selected Language: $language", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveButtonClicked(view: View) {
        val language = Language(languageId = selectedLanguage)
        languageViewModel.addLanguage(language)
        Toast.makeText(applicationContext, "Language saved: $selectedLanguage", Toast.LENGTH_SHORT)
            .show()
    }

    fun retrieveRoomDataButtonClicked(view: View) {
        languageViewModel.readAllData.observe(this, Observer { languages ->
            val selectedLang = languages.firstOrNull { it.id == 1 }?.languageId
            selectedLang?.let {
                when (it) {
                    1 -> binding.radioButton.isChecked = true
                    2 -> binding.radioButton2.isChecked = true
                    3 -> binding.radioButton3.isChecked = true
                }
                Toast.makeText(
                    applicationContext,
                    "Retrieved Language: $selectedLang",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
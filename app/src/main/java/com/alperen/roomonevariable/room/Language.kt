package com.alperen.roomonevariable.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language_table")
data class Language(
    @PrimaryKey
    val id: Int = 1, // Her zaman aynı ID'ye sahip olacak
    val languageId: Int // Saklanacak dilin ID'si (0: Turkish, 1: English, 2: German)
)
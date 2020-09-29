package com.example.supertraining.db.locale_db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class ConverterTest() {

    @TypeConverter
    fun listToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<String>::class.java).toList()


}
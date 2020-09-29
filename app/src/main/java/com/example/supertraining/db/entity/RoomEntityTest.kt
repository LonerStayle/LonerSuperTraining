package com.example.supertraining.db.entity

import androidx.room.*
import com.example.supertraining.db.converter.ConverterTest

@Entity

data class RoomEntityTest (
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    @TypeConverters(ConverterTest::class)
    var text:List<String>

)


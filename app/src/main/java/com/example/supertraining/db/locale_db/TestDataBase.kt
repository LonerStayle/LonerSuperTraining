package com.example.supertraining.db.locale_db

import android.content.Context
import androidx.room.*
import com.example.supertraining.db.locale_db.converter.ConverterTest
import com.example.supertraining.db.locale_db.dao.RoomDaoTest
import com.example.supertraining.db.locale_db.entity.RoomEntityTest

@Database(entities = [RoomEntityTest::class],version = 1,exportSchema = false )
@TypeConverters(ConverterTest::class)
 abstract class TestDataBase: RoomDatabase() {

  abstract val dataSource: RoomDaoTest

    companion object{
        @Volatile
        private var INSTANCE: TestDataBase?= null
        fun getInstance(context: Context): TestDataBase = synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context,
                TestDataBase::class.java,
                "testDatabase123"
            ).build().also {
                    INSTANCE = it
                }
        }
    }
}
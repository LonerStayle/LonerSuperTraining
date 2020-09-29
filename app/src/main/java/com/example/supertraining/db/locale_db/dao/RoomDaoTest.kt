package com.example.supertraining.db.locale_db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import com.example.supertraining.db.locale_db.entity.RoomEntityTest

@Dao
interface RoomDaoTest {

    @Query("SELECT * FROM RoomEntityTest")
    fun getAllList1():LiveData<List<RoomEntityTest>>


    @Insert
    fun insert(roomEntityTest: RoomEntityTest)


    @Delete
    fun delete(roomEntityTest: RoomEntityTest)

}
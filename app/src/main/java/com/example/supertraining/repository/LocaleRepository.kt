package com.example.supertraining.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import com.example.supertraining.db.locale_db.dao.RoomDaoTest
import com.example.supertraining.db.locale_db.entity.RoomEntityTest


interface LocaleDataSource {

    fun getAllList1(): LiveData<List<RoomEntityTest>>
    fun insert(roomEntityTest: RoomEntityTest)
    fun delete(roomEntityTest: RoomEntityTest)
}

class LocaleRepository(private val dataSource: RoomDaoTest?) : LocaleDataSource {
    override fun getAllList1() = dataSource!!.getAllList1()


    override fun insert(roomEntityTest: RoomEntityTest) {
        dataSource!!.insert(roomEntityTest)
    }

    override fun delete(roomEntityTest: RoomEntityTest) {
        dataSource!!.delete(roomEntityTest)
    }

}
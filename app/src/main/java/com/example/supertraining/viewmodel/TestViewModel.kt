package com.example.supertraining.viewmodel
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.supertraining.db.locale_db.dao.RoomDaoTest
import com.example.supertraining.db.locale_db.entity.RoomEntityTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TestViewModel(private val dataSource: RoomDaoTest):ViewModel() {
private val ioScope = CoroutineScope(Dispatchers.IO+ Job())
    val testList:LiveData<List<RoomEntityTest>>
    get() =  dataSource.getAllList1()


    fun insert(roomEntityTest: RoomEntityTest){
        ioScope.launch {
            dataSource.insert(roomEntityTest)
        }
    }

}
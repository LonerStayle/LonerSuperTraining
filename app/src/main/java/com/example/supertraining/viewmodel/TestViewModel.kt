package com.example.supertraining.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.supertraining.db.UserRssi
import com.example.supertraining.db.locale_db.dao.RoomDaoTest
import com.example.supertraining.db.locale_db.entity.RoomEntityTest
import com.example.supertraining.repository.LocaleRepository
import kotlinx.coroutines.*

class TestViewModel(private val repository: LocaleRepository) : ViewModel() {

    val testList: LiveData<List<RoomEntityTest>>
        get() = repository.getAllList1()


    fun insert(roomEntityTest: RoomEntityTest) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.insert(roomEntityTest)
            }
        }
    }

}
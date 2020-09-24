package com.example.supertraining.viewmodel
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.supertraining.db.dao.RoomDaoTest

class TestViewModelFactory(private val dataSource: RoomDaoTest):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("unchecked_cast")
            return TestViewModel(dataSource) as T

    }
}
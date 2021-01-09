package com.example.supertraining.viewmodel.factory

import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import com.example.supertraining.db.locale_db.dao.RoomDaoTest
import com.example.supertraining.repository.LocaleRepository
import com.example.supertraining.repository.NetworkRepository
import com.example.supertraining.repository.TheWalkerRepository
import com.example.supertraining.viewmodel.NetworkViewModel
import com.example.supertraining.viewmodel.TestViewModel
import com.example.supertraining.viewmodel.TheWalkerViewModel

class ViewModelFactory(
    localeDataSource: RoomDaoTest
) : ViewModelProvider.Factory {

    private val localeRepository = LocaleRepository(localeDataSource)
    private val netWorkRepository = NetworkRepository()
    private val theWalkerRepository = TheWalkerRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("unchecked_cast")
        return when {
            modelClass.isAssignableFrom(TestViewModel::class.java) -> {
                TestViewModel(localeRepository) as T
            }
            modelClass.isAssignableFrom(NetworkViewModel::class.java)->{
                NetworkViewModel(netWorkRepository) as T
            }
            modelClass.isAssignableFrom(TheWalkerViewModel::class.java)->{
                TheWalkerViewModel(theWalkerRepository) as T
            }
            else -> throw IllegalAccessException("Unknown ViewModel")
        }

    }
}
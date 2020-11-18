package com.example.supertraining.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerPush
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerUesr
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import com.example.supertraining.repository.TheWalkerRepository
import kotlinx.coroutines.launch

class TheWalkerViewModel(private val repository: TheWalkerRepository) : ViewModel() {

    private val _userData = MutableLiveData<TheWalkerUesr>()
    val userData: LiveData<TheWalkerUesr>
        get() = _userData

    private val _walkCoursList = MutableLiveData<List<TheWalkerWalkCourse>>()
    val walkCourseList:LiveData<List<TheWalkerWalkCourse>>
    get() = _walkCoursList
    

    fun snsLogin(getPush: TheWalkerPush) {
        viewModelScope.launch {

                val user = repository.snsLogin("google", "test", getPush, null)
                _userData.postValue(user)
        }
    }
    
     fun getWalkCourseList(){
         viewModelScope.launch {
             val walkCourseList = repository.getWalkCourseList()
             _walkCoursList.postValue(walkCourseList)
         }
    }
}

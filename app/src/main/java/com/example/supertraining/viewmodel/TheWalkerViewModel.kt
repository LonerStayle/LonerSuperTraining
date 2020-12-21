package com.example.supertraining.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supertraining.db.network_db.thewalker.dataholder.response.SignInCheck
import com.example.supertraining.db.network_db.thewalker.dataholder.request.Push
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import com.example.supertraining.db.network_db.thewalker.dataholder.request.Register
import com.example.supertraining.repository.TheWalkerRepository
import kotlinx.coroutines.launch

class TheWalkerViewModel(private val repository: TheWalkerRepository) : ViewModel() {

    private val _userData = MutableLiveData<SignInCheck>()
    val userData: LiveData<SignInCheck>
        get() = _userData

    private val _walkCoursList = MutableLiveData<List<TheWalkerWalkCourse>>()
    val walkCourseList: LiveData<List<TheWalkerWalkCourse>>
        get() = _walkCoursList

    private val _getToken = MutableLiveData<String>()
    val getToken:LiveData<String>
        get() = _getToken


    fun snsLogin(getPush: Push) {
        viewModelScope.launch {
            val user = repository.snsLogin("google", "test", getPush, null)
            _userData.postValue(user)
        }
    }

    fun snsRegister(register: Register) {
        viewModelScope.launch {
            val token = repository.snsRegister(
                register.deviceId,
                register.marketing,
                register.nickname,
                register.profile,
                register.pushToken,
                register.pushType,
                register.thumbnail,
                register.token,
                register.version
            )
            _getToken.value = token
        }
    }

    fun getWalkCourseList() {
        viewModelScope.launch {
            val walkCourseList = repository.getWalkCourseList()
            _walkCoursList.postValue(walkCourseList)
        }
    }
}

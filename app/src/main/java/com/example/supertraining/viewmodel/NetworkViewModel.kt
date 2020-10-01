package com.example.supertraining.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.supertraining.db.network_db.dataholder.User
import com.example.supertraining.repository.NetworkRepository
import com.google.gson.JsonElement
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkViewModel : ViewModel() {
    private val repository = NetworkRepository()

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User>
        get() = _userData


    fun userRegister(user: User,callback: Callback<JsonElement>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.userRegister(user.userId, user.userPassword, user.email)
                Log.d("registerCheck","회원 가입 성공")
            }catch (e:Exception){
                Log.d("registerCheck","회원 가입 실패")
            }
        }
    }

    fun userCheck(user: User) {
        viewModelScope.launch {
            val userData = repository.getUserData(user.userId, user.userPassword, user.email)
            _userData.postValue(userData)
        }
    }


}



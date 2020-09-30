package com.example.supertraining.viewmodel
import android.util.Log
import androidx.lifecycle.*
import com.example.supertraining.db.network_db.dataholder.User
import com.example.supertraining.repository.NetworkRepository
import com.google.gson.JsonElement
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkViewModel:ViewModel() {
    private val repository = NetworkRepository()

    private val _userData = MutableLiveData<User>()
   val userData :LiveData<User>
    get() = _userData


    fun userRegister(user:User,callback:Callback<JsonElement>){
        viewModelScope.launch {
            repository.userRegister(user.userId,user.userPassword,user.email).enqueue(callback)

        }

    }

    fun userCheck(user: User){
        viewModelScope.launch {
           val userData=  repository.getUserData(user)
            _userData.postValue(userData)
            Log.d("check","유저검색")
        }
    }


}



package com.example.supertraining.viewmodel
import android.util.Log
import androidx.lifecycle.*
import com.example.supertraining.db.network_db.dataholder.User
import com.example.supertraining.repository.NetworkRepository
import kotlinx.coroutines.launch

class NetworkViewModel:ViewModel() {
    private val repository = NetworkRepository()

    private val _userData = MutableLiveData<User>()
   val userData :LiveData<User>
    get() = _userData


    fun userRegister(user:User){
        viewModelScope.launch {
            repository.userRegister(user)
            Log.d("check","회원가입 완료")
        }

    }

    fun userCheck(user: String){
        viewModelScope.launch {
           val userData=  repository.getUserData(user)
            _userData.postValue(userData)
            Log.d("check","유저검색")
        }
    }


}
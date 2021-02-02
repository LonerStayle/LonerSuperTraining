package com.example.supertraining.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

//itRepository은 룸 썻을때
class SimpleRvTestViewModel():ViewModel() {

   private val _testList = MutableLiveData<List<String>>()
    val testList:LiveData<List<String>>
    get() = _testList


    init {
        _testList.postValue(setItemList())
    }

    private fun setItemList():List<String>{
        return listOf<String>(
            "하이하이하이", "하이하이하이", "하이하이하이", "하이하이하이", "하이하이하이",
            "하이하이하이", "하이하이하이", "하이하이하이", "하이하이하이"
        )
    }

}
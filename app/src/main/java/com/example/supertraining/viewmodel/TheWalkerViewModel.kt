package com.example.supertraining.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supertraining.db.network_db.thewalker.dataholder.request.*
import com.example.supertraining.db.network_db.thewalker.dataholder.response.*
import com.example.supertraining.repository.TheWalkerRepository
import kotlinx.coroutines.launch

class TheWalkerViewModel(private val repository: TheWalkerRepository) : ViewModel() {

    private val _userData = MutableLiveData<SignInCheck>()
    val userData: LiveData<SignInCheck>
        get() = _userData

    private val _walkCoursList = MutableLiveData<WalkList>()
    val walkCourseList: LiveData<WalkList>
        get() = _walkCoursList

    private val _getToken = MutableLiveData<String>()
    val getToken: LiveData<String>
        get() = _getToken

    private val _getMyProfile = MutableLiveData<MyProfile>()
    val getMyProfile: LiveData<MyProfile>
        get() = _getMyProfile

    private val _getNoticeList = MutableLiveData<NoticeList>()
    val getNoticeList: LiveData<NoticeList>
        get() = _getNoticeList

    private val _getQuestionList = MutableLiveData<QuestionList>()
    val getQuestionList: LiveData<QuestionList>
        get() = _getQuestionList


    private val _getSearchList = MutableLiveData<WalkList>()
    val getSearchList: LiveData<WalkList>
        get() = _getSearchList


    private val _getSpotList = MutableLiveData<SpotList>()
    val getSpotList: LiveData<SpotList>
        get() = _getSpotList





    fun snsLogin(type: String, login: Login) {
        viewModelScope.launch {
            val user = repository.snsLogin(type, login)
            _userData.postValue(user)

        }
    }


    fun snsRegister(register: Register) {
        viewModelScope.launch {
            val token = repository.snsRegister(register)
            _getToken.value = token
        }
    }

    fun getMyProfile() {
        viewModelScope.launch {
            val getMyProfile = repository.getMyProfile()
            _getMyProfile.value = getMyProfile
        }
    }

    fun userDelete(){
        viewModelScope.launch {
            repository.userDelete()
        }
    }

    fun getNoticeList(){
        viewModelScope.launch {
            val noticeList = repository.getNoticeList()
            _getNoticeList.value = noticeList
        }
    }

    fun getQuestionList(){
        viewModelScope.launch {
            val questionList = repository.getQuestionList()
            _getQuestionList.value = questionList
        }
    }

    fun feedbackSend(contactAdd: ContactAdd){
        viewModelScope.launch {
            repository.contactSend(contactAdd)
        }
    }

    fun searchWalkerList(keyWord:String){
        viewModelScope.launch {
            val searchList = repository.searchWalkerList(keyWord)
            _getSearchList.value = searchList
        }
    }

    fun addFeedBack(feedBackAdd: FeedBackAdd){
        viewModelScope.launch {
             repository.addFeedBack(feedBackAdd)
        }
    }

    fun addBookMark(bookMarkAdd: BookMarkAdd){
        viewModelScope.launch {
            repository.addBookMark(bookMarkAdd)
        }
    }

    fun addScrap(scrapAdd: ScrapAdd){
        viewModelScope.launch {
            repository.addScrap(scrapAdd)
        }
    }

    fun getSpotList(id:String){
        viewModelScope.launch {
            val spotList = repository.getSpotList(id)
            _getSpotList.value =spotList
        }
    }

    fun getWalkList() {
        viewModelScope.launch {
            val walkCourseList = repository.getWalkCourseList()
            _walkCoursList.postValue(walkCourseList)
        }
    }

}

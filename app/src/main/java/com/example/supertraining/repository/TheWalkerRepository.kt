package com.example.supertraining.repository

import com.example.supertraining.db.network_db.thewalker.TheWalkerApiClient
import com.example.supertraining.db.network_db.thewalker.dataholder.request.*
import com.example.supertraining.db.network_db.thewalker.dataholder.response.*
import com.kakao.sdk.auth.model.OAuthToken
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.Path
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface TheWalkerDataSource {

    suspend fun snsLogin(
         loginType: String,
         login: Login
    ): SignInCheck

    suspend fun snsRegister(
        register: Register
    ): String


    suspend fun userDelete()

    suspend fun getMyProfile(): MyProfile

    suspend fun getNoticeList(): NoticeList

    suspend fun getQuestionList(): QuestionList

    suspend fun contactSend(contactAdd: ContactAdd)

    suspend fun searchWalkerList(keyword: String): WalkList

    suspend fun addFeedBack(feedBackAdd: FeedBackAdd)

    suspend fun addBookMark(bookMarkAdd: BookMarkAdd)

    suspend fun addScrap( scrapAdd: ScrapAdd)

    suspend fun getSpotList(id:String):SpotList

    suspend fun getWalkCourseList(): WalkList
}

class TheWalkerRepository : TheWalkerDataSource {
    override suspend fun snsLogin(
        loginType: String,
        login: Login
    ): SignInCheck {
        return TheWalkerApiClient.api.snsLogin(loginType, login)
    }


    override suspend fun snsRegister(register: Register) =
        TheWalkerApiClient.api.snsRegister(register)


    override suspend fun userDelete() = TheWalkerApiClient.api.userDelete()

    override suspend fun getMyProfile() = TheWalkerApiClient.api.getMyProfile()

    override suspend fun getNoticeList() = TheWalkerApiClient.api.getNoticeList()

    override suspend fun getQuestionList() = TheWalkerApiClient.api.getQuestionList()

    override suspend fun contactSend(contactAdd: ContactAdd) = TheWalkerApiClient.api.contactSend(contactAdd)

    override suspend fun searchWalkerList(keyword: String) =
        TheWalkerApiClient.api.searchWalkerList(keyword)

    override suspend fun addFeedBack(feedBackAdd: FeedBackAdd) =
        TheWalkerApiClient.api.addFeedBack(feedBackAdd)

    override suspend fun addBookMark(bookMarkAdd: BookMarkAdd) =
        TheWalkerApiClient.api.addBookMark(bookMarkAdd)

    override suspend fun addScrap(scrapAdd: ScrapAdd) =
        TheWalkerApiClient.api.addScrap(scrapAdd)

    override suspend fun getSpotList(id: String) =
        TheWalkerApiClient.api.getSpotList(id)


    override suspend fun getWalkCourseList() = TheWalkerApiClient.api.getWalkCourseList()
}
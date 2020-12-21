package com.example.supertraining.repository

import com.example.supertraining.db.network_db.thewalker.TheWalkerApiClient
import com.example.supertraining.db.network_db.thewalker.dataholder.response.SignInCheck
import com.example.supertraining.db.network_db.thewalker.dataholder.request.Push
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import com.google.gson.JsonElement
import retrofit2.http.Body

interface TheWalkerDataSource{

    suspend fun snsLogin(
         loginType: String,
         version: String,
         push: Push,
         oauth: String? = null
    ): SignInCheck

    suspend fun snsRegister(
         deviceId: String?=null,
         marketing: Boolean?=null,
         nickname: String?=null,
         profile: String?=null,
         pushToken: String?=null,
         pushType: String?=null,
         thumbnail: String?=null,
         token: String?=null,
         version: String?=null
    ):String


    suspend fun getWalkCourseList(): List<TheWalkerWalkCourse>
}

class TheWalkerRepository:TheWalkerDataSource {
    override suspend fun snsLogin(
        loginType: String,
        version: String,
        push: Push,
        oauth: String?
    ): SignInCheck {

        return TheWalkerApiClient.api.snsLogin(loginType, version, push, oauth)
    }

    override suspend fun snsRegister(
        deviceId: String?,
        marketing: Boolean?,
        nickname: String?,
        profile: String?,
        pushToken: String?,
        pushType: String?,
        thumbnail: String?,
        token: String?,
        version: String?
    ): String {
        return TheWalkerApiClient.api.snsRegister(
            deviceId, marketing, nickname, profile, pushToken, pushType, thumbnail, token, version
        )
    }

    override suspend fun getWalkCourseList() = TheWalkerApiClient.api.getWalkCourseList()
}
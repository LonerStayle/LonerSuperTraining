package com.example.supertraining.repository

import com.example.supertraining.db.network_db.thewalker.TheWalkerApiClient
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerPush
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerUesr
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.Path

interface TheWalkerDataSource{

     fun snsLogin(
         loginType: String,
         version: String,
         push: TheWalkerPush,
         oauth: JsonElement? = null
    ):TheWalkerUesr

    suspend fun getWalkCourseList(): List<TheWalkerWalkCourse>
}

class TheWalkerRepository:TheWalkerDataSource {
    override  fun snsLogin(
        loginType: String,
        version: String,
        push: TheWalkerPush,
        oauth: JsonElement?
    ): TheWalkerUesr {

        return TheWalkerApiClient.api.snsLogin(loginType, version, push, oauth)
    }

    override suspend fun getWalkCourseList() = TheWalkerApiClient.api.getWalkCourseList()
}
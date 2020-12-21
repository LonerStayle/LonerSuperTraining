package com.example.supertraining.db.network_db.thewalker

import com.example.supertraining.db.network_db.thewalker.dataholder.response.SignInCheck
import com.example.supertraining.db.network_db.thewalker.dataholder.request.Push
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object TheWalkerApiClient {
    private const val BASE_URI = "http://13.125.116.170:3000/"
    val api:TheWalkerApiService = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TheWalkerApiService::class.java)
}


interface TheWalkerApiService {

    //    - type(param) : naver, kakao, google, apple
    //    - version: 해당 유저의 현재 버전
    //    - push: 푸쉬 알림을 위한 정보
    //    - type: 해당 유저의 os
    //    - token: 푸쉬 토큰 값
    //    - oauth: oauth 서버로부터 받은 토큰 등 모든 바디


    @POST("api/user/signin/sns/{type}")
     suspend fun snsLogin(
        @Path("type") loginType: String,
        @Body version: String,
        @Body push: Push,
        @Body oauth:String? = null
    ): SignInCheck


     @POST("api/user/signup/sns")
     suspend fun snsRegister(
         @Body deviceId: String?,
         @Body  marketing: Boolean?,
         @Body  nickname: String?,
         @Body  profile: String?,
         @Body  pushToken: String?,
         @Body  pushType: String?,
         @Body  thumbnail: String?,
         @Body  token: String?,
         @Body  version: String?
     ):String


    @GET("api/walk/getWalkList")
    suspend fun getWalkCourseList():List<TheWalkerWalkCourse>

}
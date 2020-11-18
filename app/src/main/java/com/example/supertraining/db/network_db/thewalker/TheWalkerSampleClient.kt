package com.example.supertraining.db.network_db.thewalker

import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerPush
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerUesr
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
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
    @FormUrlEncoded
    @POST("api/user/signin/sns/{type}")
     fun snsLogin(
        @Path("type") loginType: String,
        @Field("version") version: String,
        @Field("push") push: TheWalkerPush,
        @Field("oauth") oauth:JsonElement? = null
    ):TheWalkerUesr

    @GET("api/walk/getWalkList")
    suspend fun getWalkCourseList():List<TheWalkerWalkCourse>

}
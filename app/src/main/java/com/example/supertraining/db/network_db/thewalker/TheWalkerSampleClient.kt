package com.example.supertraining.db.network_db.thewalker

import com.example.supertraining.db.network_db.thewalker.dataholder.request.*
import com.example.supertraining.db.network_db.thewalker.dataholder.response.*
import com.kakao.sdk.auth.model.OAuthToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

object TheWalkerApiClient {
    private const val BASE_URI = "http://13.125.116.170:3000/"
    val api: TheWalkerApiService = Retrofit.Builder()
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
        @Body login: Login
    ): SignInCheck

    @POST("api/user/signup/sns")
    suspend fun snsRegister(
        @Body register: Register
    ): String


    @GET("api/user/v2/getProfile")
    suspend fun getMyProfile(): MyProfile

    @DELETE("api/user/withdraw")
    suspend fun userDelete()

    @GET("api/etc/noticeList")
    suspend fun getNoticeList(): NoticeList

    @GET("api/etc/questionList")
    suspend fun getQuestionList(): QuestionList

    @POST("api/etc/contact")
    suspend fun contactSend(@Body contactAdd: ContactAdd)

    @GET("api/walk/search")
    suspend fun searchWalkerList(@Query("keyword") keyword: String): WalkList

    @POST("api/walk/addFeedback")
    suspend fun addFeedBack(@Body feedBackAdd: FeedBackAdd)


    @POST("api/walk/addBookmark")
    suspend fun addBookMark(@Body bookMarkAdd: BookMarkAdd)

    @POST("api/walk/addScrap")
    suspend fun addScrap(@Body scrapAdd: ScrapAdd)

    @GET("api/walk/getSpotList/{id}")
    suspend fun getSpotList(@Path("id") id: String): SpotList

    @GET("api/walk/getWalkList")
    suspend fun getWalkCourseList(): WalkList


}
package com.example.supertraining.db.network_db

import com.example.supertraining.db.network_db.dataholder.User
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiClient {
    private const val BaseUri = "http://52.78.122.62"
    val api = Retrofit.Builder()
        .baseUrl(BaseUri)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

interface ApiService{
    /**
     * 가입 POST ( 필수입력 파라미터 user_id, user_password, email )
     *    수정 PUT
     *    검색 GET
     *    탈퇴 DELETE
     **/

    //유저 관련
    @POST("/api/users/")
    suspend fun userRegister(
       @Body user:User
    ):Response

    @PUT("/api/users/")
    suspend fun userDataUpdate(
        @Query("user") userId: String,
        @Query("user_password") userPassword: String,
        @Query("email") email: String
    )
    @GET("/api/users/")
    suspend fun userDataSearch(
        @Query("user") userId: String
    ): User
    @DELETE("/api/users/")
    suspend fun userDataDelete(
        @Query("user") userId: String,
        @Query("user_password") userPassword: String,
        @Query("email") email: String
    )
}
package com.example.supertraining.db.network_db

import com.example.supertraining.db.network_db.dataholder.User
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object ApiClient {
    private const val BaseUri = "http://52.78.122.62/api/"
    val api: ApiService = Retrofit.Builder()
        .baseUrl(BaseUri)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

interface ApiService {
    /**
     * 가입 POST ( 필수입력 파라미터 user_id, user_password, email )
     *    수정 PUT
     *    검색 GET
     *    탈퇴 DELETE
     **/

    //유저 관련
    @FormUrlEncoded
    @POST("users")
    suspend fun userRegister(
        @Field("user_id") userId: String,
        @Field("user_password") userPassword: String,
        @Field("email") email: String ): Call<JsonElement>

    @PUT("users")
    suspend fun userDataUpdate(
        @Query("user_id") userId: String,
        @Query("user_password") userPassword: String,
        @Query("email") email: String
    )

    @GET("users")
    suspend fun userDataSearch(
        @Query("user") user: User,
    ): User

    @DELETE("users")
    suspend fun userDataDelete(
        @Query("user_id") userId: String,
        @Query("user_password") userPassword: String,
        @Query("email") email: String
    )
}
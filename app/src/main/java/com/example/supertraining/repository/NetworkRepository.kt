package com.example.supertraining.repository

import com.example.supertraining.db.network_db.ApiClient
import com.example.supertraining.db.network_db.dataholder.User
import okhttp3.Response
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query

interface DataSource{

    suspend fun userRegister(
         user:User
    ):Response
    suspend fun getUserData(
         userId: String,

    ):User

}

class NetworkRepository:DataSource {
    override suspend fun userRegister(user:User): Response {
       return ApiClient.api.userRegister(user)
    }

    override suspend fun getUserData(userId: String): User {
     return ApiClient.api.userDataSearch(userId )
    }
}
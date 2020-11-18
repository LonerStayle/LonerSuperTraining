package com.example.supertraining.repository

import com.example.supertraining.db.network_db.sample.ApiClient
import com.example.supertraining.db.network_db.sample.dataholder.User
import com.google.gson.JsonElement


interface DataSource {

    suspend fun userRegister(
        userId: String,
        userPassword: String,
        userEmail: String,
    ): JsonElement

    suspend fun getUserData(
        userId: String, userPassword: String, userEmail: String
    ): User

}

class NetworkRepository : DataSource {
    override  suspend fun userRegister(
        userId: String,
        userPassword: String,
        userEmail: String,
    ): JsonElement {
        return ApiClient.api.userRegister(userId, userPassword, userEmail)
    }

    override suspend fun getUserData(
        userId: String,
        userPassword: String,
        userEmail: String
    ): User {
        return ApiClient.api.userDataSearch(userId,userPassword,userEmail)
    }

}
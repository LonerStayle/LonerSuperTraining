package com.example.supertraining.db.network_db.sample.dataholder

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("user_id")
    val userId: String,
    @SerializedName("user_password")
    val userPassword: String,
    @SerializedName("email")
    val email: String,
)
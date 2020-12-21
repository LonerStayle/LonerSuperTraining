package com.example.supertraining.db.network_db.thewalker.dataholder

data class User(
    val _id: String?,
    val birthyear: Int?,
    val email: String?,
    val gender: String?,
    val nickname: String?,
    val profile: Any?,
    val setting: Setting?,
    val thumbnail: Any?
)
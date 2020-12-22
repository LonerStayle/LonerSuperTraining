package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInProfile(
    val birthyear: String,
    val email: String,
    val gender: String,
    val nickname: String,
    val profile: String
):Parcelable
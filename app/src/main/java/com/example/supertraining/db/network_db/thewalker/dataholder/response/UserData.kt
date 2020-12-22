package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    val _id: String?,
    val birthyear: Int?,
    val email: String?,
    val gender: String?,
    val nickname: String?,
    val profile: String?,
    val setting: Setting?,
    val thumbnail: String?
):Parcelable
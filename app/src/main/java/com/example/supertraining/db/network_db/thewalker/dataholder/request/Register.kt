package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Register(
    val deviceId: String?,
    val marketing: Boolean?,
    val nickname: String?,
    //이미지
    val profile: String?,
    val pushToken: String? = null,
    val pushType: String? = null,
    //작은 섬네일 이미지
    val thumbnail: String? = null,
    val token: String?,
    val version: String? = null
):Parcelable

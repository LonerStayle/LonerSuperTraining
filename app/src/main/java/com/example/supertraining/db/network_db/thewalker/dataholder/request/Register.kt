package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Register(
    val deviceId: String?,
    val marketing: Boolean?,
    val nickname: String?,
    val profile: String?,
    val pushToken: String?,
    val pushType: String?,
    val thumbnail: String?,
    val token: String?,
    val version: String?
):Parcelable
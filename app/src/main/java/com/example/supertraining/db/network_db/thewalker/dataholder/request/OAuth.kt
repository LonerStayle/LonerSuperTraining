package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OAuth(
    val accessToken: String?,
    val expires: Int?,
    val ttl: Int?

):Parcelable
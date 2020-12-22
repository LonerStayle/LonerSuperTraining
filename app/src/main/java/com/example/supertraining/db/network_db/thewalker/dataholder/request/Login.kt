package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(
    val oauth: Oauth?,
    val push: Push?,
    val version: String?
):Parcelable
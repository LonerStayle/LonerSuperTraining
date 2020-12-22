package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyProfile(
    val userData: UserData
):Parcelable
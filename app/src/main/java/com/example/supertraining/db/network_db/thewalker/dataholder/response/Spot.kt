package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Spot(
    val _id: String?,
    val createdAt: String?,
    val location: Location?,
    val mp3: String?,
    val name: String?,
    val thumbnail: String?,
    val updatedAt: String?
):Parcelable
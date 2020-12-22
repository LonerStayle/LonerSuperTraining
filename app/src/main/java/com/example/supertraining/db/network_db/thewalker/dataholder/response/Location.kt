package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Location(
    val coordinates: List<String>?,
    val type: String?
):Parcelable
package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeedBackAdd(
    val id: String?,
    val score: String?
):Parcelable
package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val _id: String?,
    val content: String?,
    val createdAt: String?,
    val title: String?,
    val updatedAt: String?
):Parcelable
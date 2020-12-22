package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactAdd(
    val content: String?,
    val email: String?,
    val name: String?,
    val title: String?
):Parcelable
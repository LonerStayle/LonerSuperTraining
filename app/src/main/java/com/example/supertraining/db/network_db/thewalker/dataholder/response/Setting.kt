package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Setting(
    val alarm: Boolean?,
    val marketing: Boolean?,
    val shareLocation: Boolean?,
    val transportation: String?
):Parcelable
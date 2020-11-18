package com.example.supertraining.db.network_db.thewalker.dataholder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TheWalkerPush (
    val type:String,
    val token:String
):Parcelable
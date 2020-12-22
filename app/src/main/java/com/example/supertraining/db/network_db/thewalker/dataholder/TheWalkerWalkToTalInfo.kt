package com.example.supertraining.db.network_db.thewalker.dataholder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TheWalkerWalkToTalInfo (
    val distance:String,
    val countSpot:String,
    val time:String,
):Parcelable
package com.example.supertraining.db.network_db.thewalker.dataholder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TheWalkerWalkCourse(
    val id:String,
    val name:String,
    val description:String,
    val price:Int,
    val thumbnail:String,
    val toTalInfo: TheWalkerWalkToTalInfo,
    val curator:String,
    val spotList:List<String>,
):Parcelable
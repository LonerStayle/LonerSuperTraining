package com.example.supertraining.db.network_db.thewalker.dataholder

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.sql.Date


data class TheWalkerWalkCourse(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("price")
    val price:Int,
        @SerializedName("thumbnail")
    val thumbnail:String,
        @SerializedName("totalInfo")
    val toTalInfo: TheWalkerWalkToTalInfo,
        @SerializedName("curator")
    val curator:String,
        @SerializedName("spotList")
    val spotList:List<String>,


)
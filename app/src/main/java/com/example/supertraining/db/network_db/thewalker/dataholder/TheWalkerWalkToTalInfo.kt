package com.example.supertraining.db.network_db.thewalker.dataholder

import com.google.gson.annotations.SerializedName


data class TheWalkerWalkToTalInfo (

    @SerializedName("distance")
    val distance:String,
    @SerializedName("countSpot")
    val countSpot:String,
    @SerializedName("time")
    val time:String,

)
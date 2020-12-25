package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInCheck(
    val idToken: String,
    val signInProfile: SignInProfile,
    val token:String,
    val signedUp: Boolean
):Parcelable
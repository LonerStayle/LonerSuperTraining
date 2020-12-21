package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import com.example.supertraining.db.network_db.thewalker.dataholder.Profile
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignInCheck(
    val idToken: String,
    val profile: Profile,
    val signedUp: Boolean
):Parcelable
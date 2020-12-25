package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class OAuthKaKao(
  val access_token:String,
  val access_tokenExpiresAt: Long,
  val refresh_token: String,
  val refresh_tokenExpiresAt: Long,
  val scopes: List<String>? = null
):Parcelable
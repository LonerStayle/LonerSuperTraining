package com.example.supertraining.db.network_db.thewalker.dataholder.request

import android.os.Parcelable
import com.google.gson.JsonObject
import com.kakao.sdk.auth.model.OAuthToken
import kotlinx.android.parcel.Parcelize
import okhttp3.RequestBody
import org.json.JSONObject

@Parcelize
data class Login(
    val version: String?,
    val oauth: Any,
):Parcelable
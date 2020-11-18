package com.example.supertraining.db.network_db.thewalker.dataholder

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TheWalkerProfile (

    //    - nickname: 닉네임
    val nickname:String,
    //    - profile: 프로필 이미지 주소
    val profile:String,
    //    - email: 이메일 주소
    val email:String,
    //    - gender: 성별
    val gender:String,
    //    - birthyear: 태어난 연도
    val birthyear:String,
): Parcelable
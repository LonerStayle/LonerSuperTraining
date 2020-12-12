package com.example.supertraining.db.network_db.thewalker.dataholder

import android.os.Parcelable
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerProfile
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TheWalkerUesr(

//signedUp: 가입 여부
    val signedUp: Boolean,
//    - idToken: 고유 id를 암호화한 값
    val idToken: String,
    //    - profile: 프로필 데이터
    val profile: TheWalkerProfile

):Parcelable
package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import com.example.supertraining.db.network_db.thewalker.dataholder.TheWalkerWalkCourse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WalkList(
    val walkList: List<TheWalkerWalkCourse>
):Parcelable


package com.example.supertraining.db.network_db.thewalker.dataholder.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionList(
    val questionList: List<Question>?
):Parcelable
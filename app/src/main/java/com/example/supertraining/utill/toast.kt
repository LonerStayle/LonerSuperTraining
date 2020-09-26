package com.example.supertraining.utill

import android.app.Application
import android.content.Context
import android.widget.Toast

fun Context.toastShow(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

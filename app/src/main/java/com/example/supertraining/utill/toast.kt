package com.example.supertraining.utill

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.supertraining.view.base.BaseFragment

 fun Context.toastShortShow(stringMessage: String) =
     Toast.makeText(this, stringMessage, Toast.LENGTH_SHORT).show()

 fun Context.toastShortShow(resId: Int) =
     Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()

 fun Context.toastLongShow(stringMessage: String?) =
     Toast.makeText(this, stringMessage, Toast.LENGTH_SHORT).show()

 fun Context.toastLongShow(resId: Int) =
     Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()


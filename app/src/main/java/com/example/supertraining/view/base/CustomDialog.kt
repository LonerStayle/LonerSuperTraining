package com.example.supertraining.view.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

class CustomDialog<VDB : ViewDataBinding>(
    context: Context,
    @LayoutRes val layoutId: Int

) : Dialog(context) {

    var binding: VDB = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.run {
             lifecycleOwner = this.lifecycleOwner
        }
    }



    fun setWindowManager() {
        binding.apply {
            WindowManager.LayoutParams().let {


                it.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
                it.dimAmount = 0.6f
//                 val dp = context.resources.displayMetrics.density
//                 it.height = (350 * dp).toInt()

                it.gravity = Gravity.BOTTOM or Gravity.CENTER
                window?.attributes = it
            }
            //다이얼로그 레이아웃 백그라운드 투명
//             window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

}


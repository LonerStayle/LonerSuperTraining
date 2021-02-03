package com.example.supertraining.view.activity

import android.content.Intent
import android.util.Log
import android.view.WindowManager
import com.example.supertraining.R
import com.example.supertraining.databinding.ActivityTransBinding
import com.example.supertraining.view.base.BaseActivity
import com.example.supertraining.view.utill.toastShortShow

var runCheck = false
class TransAct : BaseActivity<ActivityTransBinding>(R.layout.activity_trans) {
    override fun ActivityTransBinding.setDataBind() {
        btnMain.setOnClickListener { startActivity(Intent(this@TransAct, MainActivity::class.java)) }
    }

    override fun ActivityTransBinding.onCreate() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        Log.d("onCreate","온 크리에이트에서 메시지 한번")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume","온 리쥼에서 메시지 한번")
    }

    override fun onStop() {
        super.onStop()
        if(!runCheck) {
            startActivity(Intent(applicationContext, TransAct::class.java))
            runCheck = true
        }else{
            runCheck = false
        }
    }

    override fun onBackPressed() {
        toastShortShow("뒤로가기 클릭했다.")
        return
    }

    override fun onRestart() {
        super.onRestart()
        runCheck = false
    }
}
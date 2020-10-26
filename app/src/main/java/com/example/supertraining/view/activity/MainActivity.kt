package com.example.supertraining.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.supertraining.R
import com.example.supertraining.databinding.ActivityMainBinding
import com.example.supertraining.view.utill.tedPermissionCheck
import com.example.supertraining.view.utill.toastShortShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000L)
            setTheme(R.style.AppTheme)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).run {
            tedPermissionCheck(this@MainActivity) { toastShortShow("ㅎㅇ") }
            root
        }
    }


}
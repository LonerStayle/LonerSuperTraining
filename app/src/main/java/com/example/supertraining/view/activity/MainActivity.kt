package com.example.supertraining.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.supertraining.R
import com.example.supertraining.databinding.ActivityMainBinding
import com.example.supertraining.view.pref.PreferencesControl
import com.example.supertraining.view.utill.tedPermissionCheck
import com.example.supertraining.view.utill.toastShortShow


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000L)
        setTheme(R.style.AppTheme)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).run {
            PreferencesControl.getInstance(this@MainActivity).appFirstMusicPlay = false

            tedPermissionCheck(this@MainActivity) { toastShortShow("ㅎㅇ") }
            root
        }
    }


//
//    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
//        val action = event.action
//
//        return when (event.keyCode) {
//
//            KeyEvent.KEYCODE_VOLUME_UP -> {
//                if (action == KeyEvent.ACTION_DOWN) {
//                    val intent = Intent("activity-says-hi")
//                    intent.putExtra("volume",+1)
//                    sendVolumeBroadcast(intent)
//                }
//                true
//            }
//
//            KeyEvent.KEYCODE_VOLUME_DOWN -> {
//                if (action == KeyEvent.ACTION_DOWN) {
//                    val intent = Intent("activity-says-hi")
//                    intent.putExtra("volume",-1)
//                    sendVolumeBroadcast(intent)
//                }
//                true
//            }
//            else -> super.dispatchKeyEvent(event)
//        }
//    }

//    private fun sendVolumeBroadcast(intent:Intent) {
//          LocalBroadcastManager.getInstance(this@MainActivity).sendBroadcast(intent)
//    }


}
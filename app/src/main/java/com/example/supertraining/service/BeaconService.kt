package com.example.supertraining.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.db.UserRssi
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.view.utill.toastLongShow
import com.example.supertraining.viewmodel.factory.ViewModelFactory
import com.minew.beacon.BluetoothState
import com.minew.beacon.MinewBeacon
import com.minew.beacon.MinewBeaconManager
import com.minew.beacon.MinewBeaconManagerListener
import java.util.*
import com.example.supertraining.R
import com.example.supertraining.view.activity.MainActivity

class BeaconService : Service() {

     var mMinewBeaconManager: MinewBeaconManager? = null
     val comp = UserRssi()
    private val localBinder = LocalBinder()


    fun setInitService(){
//        val pendingIntent = NavDeepLinkBuilder(this@BeaconService)
//            .setGraph(R.navigation.navi)
//            .setDestination(R.id.mainFragment)
//            .setDestination(R.id.serviceTestFragment)
//            .createPendingIntent()

        val intent = Intent(this@BeaconService,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this@BeaconService, ServiceTest.SERVICE_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_dialer)
            .setContentTitle("비콘 확인중입니다")
            .setContentText("비콘 테스트")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(7777,builder)
    }


    override fun onBind(p0: Intent?) = localBinder

    inner class LocalBinder : Binder() {
        fun getService(): BeaconService {
            return this@BeaconService
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mMinewBeaconManager?.stopScan()
    }
}
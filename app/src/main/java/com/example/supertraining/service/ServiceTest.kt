package com.example.supertraining.service

import android.app.Service
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.R

class ServiceTest : Service() {
    companion object {
        const val SERVICE_CHANNEL_ID = "ServiceChannelID"
        const val FORE_GROUND_SERVICE_ID = 191919
    }

    override fun onCreate() {
        super.onCreate()

//        /val notificationIntent = Intent(this, MainActivity::class.java)
        //val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notificationNavDeepLink = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.navi)
            .setDestination(R.id.serviceTestFragment)
//            .setArguments(args)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this, SERVICE_CHANNEL_ID) // channel ID
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText("test")
            //.setContentIntent(pendingIntent)
            .setContentIntent(notificationNavDeepLink)
        startForeground(FORE_GROUND_SERVICE_ID, builder.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("flowCheck", "onStartCommand 첫시작")
//        stopSelf()
//바로 onDestory로 감
        val localBroadcastManager = LocalBroadcastManager.getInstance(this@ServiceTest)
        val intent = Intent("intent_action")
        intent.putExtra("test", "LocalBroadcastManager를 통해 서비스에서 날아왔습니다. ")
        localBroadcastManager.sendBroadcast(intent)

        //TODO : 서비스 처음 시작시 할 동작 정의.

        return START_REDELIVER_INTENT
    }


    override fun onDestroy() {
        Log.d("flowCheck", "Service onDestory 죽음 ")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?) = null

}
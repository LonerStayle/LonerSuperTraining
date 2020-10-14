package com.example.supertraining.component.broadcast_receiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.R
import com.example.supertraining.view.activity.MainActivity

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    lateinit var notificationManager: NotificationManager
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("receiverCheck", "Recived intent:$intent")

        notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        deliverNotification(context)

    }

    private fun deliverNotification(context: Context) {
        //기존 액티비티 모드
//        val contentIntent = Intent(context, MainActivity::class.java)
//        val contentPendingIntent = PendingIntent.getActivity(
//            context,
//            NOTIFICATION_ID,
//            contentIntent,
//            PendingIntent.FLAG_UPDATE_CURRENT
//        )

        val naviPendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.navi)
            .setDestination(R.id.mainFragment)
            .setDestination(R.id.broadCastTestFragment)
            .createPendingIntent()

        val builder =
            NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Alert")
                .setContentText("This is repeating alarm")
                .setContentIntent(naviPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        notificationManager.notify(NOTIFICATION_ID, builder.build())

    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Stend up notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.also {
                it.lightColor = Color.RED
                it.enableVibration(true)
                it.description = "AlarmManager Tests"
                notificationManager.createNotificationChannel(it)
            }
        }
    }
}
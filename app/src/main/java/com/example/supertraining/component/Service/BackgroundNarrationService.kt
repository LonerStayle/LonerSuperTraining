package com.example.supertraining.component.Service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.R
import com.example.supertraining.component.Service.ServiceTest.Companion.SERVICE_CHANNEL_ID
import com.example.supertraining.view.utill.toastShortShow


class BackgroundNarrationService:Service() {

    private var mediaPlayer = MediaPlayer()


    override fun onCreate() {
        super.onCreate()

        val pendingIntent = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.navi)
            .setDestination(R.id.mainFragment)
            .setDestination(R.id.serviceTestFragment)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this,SERVICE_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_dialer)
            .setContentTitle("음악 플레이중 입니다.")
            .setContentText("음악 테스트")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(ServiceTest.FORE_GROUND_SERVICE_ID,builder)
        
        
        mediaPlayer = MediaPlayer.create(this,R.raw.sound_sample_narration)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(1.0f,1.0f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
        toastShortShow("음악실행중 입니다.")
        return startId
    }

    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }



    override fun onBind(p0: Intent?): IBinder? = null

}
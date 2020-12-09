package com.example.supertraining.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.R
import com.example.supertraining.service.ServiceTest.Companion.SERVICE_CHANNEL_ID
import com.example.supertraining.view.utill.toastShortShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class BackgroundSoundService : Service() {

    var mediaPlayer = MediaPlayer()
    private val localBinder = LocalBinder()
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())


    override fun onCreate() {
        super.onCreate()
        val pendingIntent = NavDeepLinkBuilder(this)
            .setGraph(R.navigation.navi)
            .setDestination(R.id.mainFragment)
            .setDestination(R.id.serviceTestFragment)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(this, SERVICE_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_dialer)
            .setContentTitle("음악 플레이중 입니다.")
            .setContentText("음악 테스트")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(ServiceTest.FORE_GROUND_SERVICE_ID, builder)
        toastShortShow("서비스 onCreate 함수 실행 ")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        CoroutineScope(Dispatchers.IO).launch {
            mediaPlayer = MediaPlayer.create(
                baseContext, Uri.parse(intent!!.getStringExtra("music"))
            )
            mediaPlayer.isLooping = true
            mediaPlayer.setVolume(0.5f, 0.5f)
            mediaPlayer.start()
        }

        toastShortShow("서비스 onStartCommend 함수 실행")
        return START_NOT_STICKY
    }


    inner class LocalBinder : Binder() {
        fun getService(): BackgroundSoundService = this@BackgroundSoundService
    }


    fun setSoundChange(uri: String) {
//        mediaPlayer.stop()

        mediaPlayer.reset()
        mediaPlayer.setDataSource(baseContext, Uri.parse(uri))
        mediaPlayer.prepare()
        mediaPlayer.start()


    }


    override fun onDestroy() {
        mediaPlayer.stop()
        mediaPlayer.release()

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return localBinder
    }
}
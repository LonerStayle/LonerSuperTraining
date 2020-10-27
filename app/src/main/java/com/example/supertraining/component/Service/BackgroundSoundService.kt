package com.example.supertraining.component.Service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.R
import com.example.supertraining.component.Service.ServiceTest.Companion.MUSIC_CONTROL_PLAY_CONTROL
import com.example.supertraining.component.Service.ServiceTest.Companion.MUSIC_CONTROL_SEEK_TO_NEXT
import com.example.supertraining.component.Service.ServiceTest.Companion.MUSIC_CONTROL_SEEK_TO_PREV
import com.example.supertraining.component.Service.ServiceTest.Companion.SERVICE_CHANNEL_ID
import com.example.supertraining.view.utill.toastShortShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BackgroundSoundService : Service() {

    private var mediaPlayer = MediaPlayer()
    private val localBinder = LocalBinder()

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
        mediaPlayer = MediaPlayer.create(this, R.raw.music_sample_background)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(1.0f, 1.0f)


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        CoroutineScope(Dispatchers.IO).launch {
            when (intent?.getIntExtra(ServiceTest.MUSIC_CONTROL_MODE_CHECK, 0)) {
                MUSIC_CONTROL_PLAY_CONTROL -> {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    } else
                        mediaPlayer.start()
                }
                MUSIC_CONTROL_SEEK_TO_NEXT -> {
                    if(mediaPlayer.isPlaying)
                    mediaPlayer.seekTo(mediaPlayer.currentPosition+5000)
                    else
                        return@launch
                }
                MUSIC_CONTROL_SEEK_TO_PREV-> {
                    if(mediaPlayer.isPlaying)
                    mediaPlayer.seekTo(mediaPlayer.currentPosition-5000)
                    else
                        return@launch
                }

                else -> mediaPlayer.start()
            }

        }

        toastShortShow("음악 컨트롤")
        return START_NOT_STICKY
    }



override fun onBind(intent: Intent?): IBinder? {
    return localBinder
}

inner class LocalBinder : Binder() {
    fun getService(): BackgroundSoundService = this@BackgroundSoundService
}

override fun onDestroy() {
    mediaPlayer.stop()
    mediaPlayer.release()

    super.onDestroy()
}
}
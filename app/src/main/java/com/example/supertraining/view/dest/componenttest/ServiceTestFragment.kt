package com.example.supertraining.view.dest.componenttest

import android.content.*
import android.media.AudioManager
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.supertraining.R
import com.example.supertraining.component.Service.BackgroundNarrationService
import com.example.supertraining.component.Service.BackgroundSoundService
import com.example.supertraining.component.Service.ServiceTest
import com.example.supertraining.databinding.FragmentServiceTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.Contents


class ServiceTestFragment() :
    BaseFragment<FragmentServiceTestBinding>(R.layout.fragment_service_test) {

    private lateinit var bindingService: BackgroundSoundService

    override fun FragmentServiceTestBinding.setDataBind() {
        this.thisFragment = this@ServiceTestFragment
        setBroadCastReceiver()
        setVolumeControl()

    }

    private fun setBroadCastReceiver() {
        // 싱글톤 단점을  억지로 막는법.. 값을 보내는 쪽에서 count를 둔다. 그럴러면 아래걸 지우고 service쪽에 둬야함
        // 억지스러운 내방식임, 좋은 방식은 아니라 아예 재 반복을 막아야 의미가 있음
//        var count = 0
        val messageReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
//                if(count > 0)
//                    return
//                ++count

                val test = intent.getStringExtra("test")
                Toast.makeText(context, test, Toast.LENGTH_SHORT).show()
            }
        }
        //동적 브로드캐스트 리시버는 intentFilter 지정 필요함.
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(messageReceiver, IntentFilter("intent_action"))
    }

    fun setButtonServiceBindStart(v: View) {
        val bindServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {}
            override fun onServiceDisconnected(p0: ComponentName?) {
                Toast.makeText(
                    requireContext(),
                    "예기치 못한 사정으로 바인드 연결이 끊겼습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        requireContext().bindService(
            Intent(requireContext(), ServiceTest::class.java),
            bindServiceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    fun setButtonMediaPlayerClickListener(v: View) {
        val intent = Intent(requireContext(), BackgroundSoundService::class.java)
        requireContext().startService(intent)
    }

    fun setButtonServiceStartClickListener(v: View) {
        requireContext().startService(Intent(requireContext(), ServiceTest::class.java))
    }

    fun setButtonNarrationStartClickListener(v: View) {
        requireContext().startService(
            Intent(
                requireContext(),
                BackgroundNarrationService::class.java
            )
        )
    }


    fun setButtonMediaPlayerPauseClickListener(v: View) {
        val intent = Intent(requireContext(), BackgroundSoundService::class.java)
        intent.putExtra(
            ServiceTest.MUSIC_CONTROL_MODE_CHECK,
            ServiceTest.MUSIC_CONTROL_PLAY_CONTROL
        )
        requireContext().startService(intent)
//        val bindServiceConnection = object : ServiceConnection {
//            override fun onServiceConnected(componentName: ComponentName?, iBinder: IBinder?) {
//                val binder = iBinder as BackgroundSoundService.LocalBinder
//                bindingService = binder.getService()
//
//                CoroutineScope(Dispatchers.IO).launch {
//                    delay(200L)
//                    bindingService.let {
//                        if (it.mediaPlayer.isPlaying)
//                            it.mediaPlayer.pause()
//                        else
//                            it.mediaPlayer.start()
//                    }
//                }
//            }
//
//            override fun onServiceDisconnected(componentName: ComponentName?) {
//            }
//        }

//        val intent = Intent(requireContext(), BackgroundSoundService::class.java)
//        context?.bindService(
//            intent,
//            bindServiceConnection,
//            Context.BIND_AUTO_CREATE
//        )


    }

    fun setButtonMusicSeekToPrevClickListener(v: View) {
        val intent = Intent(requireContext(), BackgroundSoundService::class.java)
        intent.putExtra(
            ServiceTest.MUSIC_CONTROL_MODE_CHECK,
            ServiceTest.MUSIC_CONTROL_SEEK_TO_PREV
        )
        requireContext().startService(intent)
    }

    fun setButtonMusicSeekToNextClickListener(v: View) {
        val intent = Intent(requireContext(), BackgroundSoundService::class.java)
        intent.putExtra(
            ServiceTest.MUSIC_CONTROL_MODE_CHECK,
            ServiceTest.MUSIC_CONTROL_SEEK_TO_NEXT
        )
        requireContext().startService(intent)
    }

    fun setButtonNarrationChangeClickListener(v: View) {
        val intent = Intent(requireContext(), BackgroundSoundService::class.java)

        intent.putExtra(
            ServiceTest.MUSIC_SELECT_CHANGE,
            true
        )
        intent.putExtra(
            ServiceTest.MUSIC_URI_CHANGE,
            Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "sound_sample_narration",
                "raw",
                requireContext().packageName
            ).toString()
        )


        intent.putExtra(
            ServiceTest.MUSIC_CONTROL_MODE_CHECK,
            ServiceTest.MUSIC_CONTROL_PLAY_CONTROL
        )

        requireContext().startService(intent)
    }

    fun setButtonBackgroundMusicPlayClickListener(v: View) {
        val intent = Intent(requireContext(), BackgroundSoundService::class.java)
        intent.putExtra(
            ServiceTest.MUSIC_SELECT_CHANGE,
            true
        )
        intent.putExtra(
            ServiceTest.MUSIC_URI_CHANGE,
            Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "music_sample_background",
                "raw",
                requireContext().packageName
            ).toString()
        )

        intent.putExtra(
            ServiceTest.MUSIC_CONTROL_MODE_CHECK,
            ServiceTest.MUSIC_CONTROL_PLAY_CONTROL
        )

        requireContext().startService(intent)
    }

    fun setButtonVolumeControlVisibleClickListener(v: View) {
        with(binding) {

            seekBarVolumeControl.let {
                when (it.visibility) {
                    View.VISIBLE -> it.visibility = View.GONE
                    View.GONE -> it.visibility = View.VISIBLE
                    else -> return@let
                }
            }

        }
    }


    private fun FragmentServiceTestBinding.setVolumeControl() {
        val audio = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        val currentVolume = audio!!.getStreamVolume(AudioManager.STREAM_MUSIC)
        val maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC)


        seekBarVolumeControl.max = maxVolume
        seekBarVolumeControl.progress = currentVolume
        seekBarVolumeControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {

                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

}
package com.example.supertraining.view.dest.component_test

import android.content.*
import android.media.AudioManager
import android.os.IBinder
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.supertraining.R
import com.example.supertraining.service.BackgroundNarrationService
import com.example.supertraining.service.BackgroundSoundService
import com.example.supertraining.service.ServiceTest
import com.example.supertraining.databinding.FragmentServiceTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.pref.PreferencesControl
import com.example.supertraining.view.utill.Contents


class ServiceTestFragment() :
    BaseFragment<FragmentServiceTestBinding>(R.layout.fragment_service_test) {

    private lateinit var bindingService: BackgroundSoundService
    private var serviceBindUse: Boolean = false
    private var selectNarrationsSound: String? = null
    private var startNarrationsSound: String? = null

    private val sampleNarrationSound by lazy {
        Contents.IMAGE_URL_DEFAULT_FILE_PATH +
                resources.getIdentifier(
                    "sound_sample_narration",
                    "raw",
                    requireContext().packageName
                ).toString()
    }

    private val sampleMusicSound by lazy {
        Contents.IMAGE_URL_DEFAULT_FILE_PATH +
                resources.getIdentifier(
                    "music_sample_background",
                    "raw",
                    requireContext().packageName
                ).toString()
    }

    private val bindConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as BackgroundSoundService.LocalBinder
            bindingService = binder.getService()
            serviceBindUse = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            serviceBindUse = false
        }
    }

    private val audio by lazy { context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager? }

    override fun FragmentServiceTestBinding.setDataBind() {
        this.thisFragment = this@ServiceTestFragment
        setBroadCastReceiver()
        setVolumeControl()

        Intent(requireContext(), BackgroundSoundService::class.java).let {
            context?.bindService(
                it,
                bindConnection,
                Context.BIND_AUTO_CREATE
            )
        }

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
        PreferencesControl.getInstance(requireContext()).setNarrationSoundPath = sampleMusicSound

        bindingService.let {

            //선택한 사운드
            selectNarrationsSound =
                PreferencesControl.getInstance(requireContext()).setNarrationSoundPath

            //음악이 흐르는 사운드
            startNarrationsSound =
                PreferencesControl.getInstance(requireContext()).setStartNarrationsSoundPath

            when {
                //음악을 맨 처음 시작했을때 혹은 음악을 변경했을때
                !PreferencesControl.getInstance(requireContext()).appFirstMusicPlay -> {
                    PreferencesControl.getInstance(requireContext()).appFirstMusicPlay = true

                    Intent(context, BackgroundSoundService::class.java).let {intent->
                        intent.putExtra("music",sampleMusicSound)
                        context?.startService(intent)
                    }
                    PreferencesControl.getInstance(requireContext()).setStartNarrationsSoundPath =
                        selectNarrationsSound
                }


                //음악 체인지
                selectNarrationsSound != startNarrationsSound -> {
                    it.setSoundChange(sampleMusicSound)

                    PreferencesControl.getInstance(requireContext()).setStartNarrationsSoundPath =
                        selectNarrationsSound
                    return@let
                }

                //현재 음악 플레이 중 일때
                        it.mediaPlayer.isPlaying -> {
                    it.mediaPlayer.pause()
                }

                //현재 음악이 멈춰 있을 때
                        !it.mediaPlayer.isPlaying -> {
                    it.mediaPlayer.start()
                }

            }
        }
    }

    fun setButtonNarrationChangeClickListener(v: View) {

        PreferencesControl.getInstance(requireContext()).setNarrationSoundPath =
            sampleNarrationSound

        bindingService.let { it ->
            //선택한 사운드
            selectNarrationsSound =
                PreferencesControl.getInstance(requireContext()).setNarrationSoundPath

            //음악이 흐르는 사운드
            startNarrationsSound =
                PreferencesControl.getInstance(requireContext()).setStartNarrationsSoundPath

            when {
                //음악을 맨 처음 시작했을때
                !PreferencesControl.getInstance(requireContext()).appFirstMusicPlay -> {
                    PreferencesControl.getInstance(requireContext()).appFirstMusicPlay = true

                    Intent(context, BackgroundSoundService::class.java).let { intent ->
                        intent.putExtra("music",sampleNarrationSound)
                        context?.startService(intent)
                    }

                    PreferencesControl.getInstance(requireContext()).setStartNarrationsSoundPath =
                        startNarrationsSound

                }

                //음악을 변경할 때
                selectNarrationsSound != startNarrationsSound -> {
                    it.setSoundChange(sampleNarrationSound)

                    PreferencesControl.getInstance(requireContext()).setStartNarrationsSoundPath =
                        selectNarrationsSound
                    return@let
                }

                //현재 음악 플레이 중 일때
                it.mediaPlayer.isPlaying -> {

                    it.mediaPlayer.pause()
                }

                //현재 음악이 멈춰 있을 때
                !it.mediaPlayer.isPlaying -> {

                    it.mediaPlayer.start()
                }

            }
        }

    }

    fun setButtonMusicSeekToPrevClickListener(v: View) {

        bindingService.let {
            it.mediaPlayer.seekTo(it.mediaPlayer.currentPosition - 5000)
        }
    }

    fun setButtonMusicSeekToNextClickListener(v: View) {
        bindingService.let {
            it.mediaPlayer.seekTo(it.mediaPlayer.currentPosition + 5000)
        }
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

        seekBarVolumeControl.max = audio!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        seekBarVolumeControl.progress = audio!!.getStreamVolume(AudioManager.STREAM_MUSIC)

        seekBarVolumeControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {

                audio!!.setStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    progress,
                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

//    open class VolumeChangeObserver(handler: Handler?) :
//        ContentObserver(handler) {
//         var volume:Int? =null
//
//        override fun onChange(selfChange: Boolean) {
//            super.onChange(selfChange)
//            onChangeLogic(volume)
//
//        }
//        open fun onChangeLogic(volume: Int?) = Unit
//    }

    //별로임
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        super.onViewCreated(view, savedInstanceState)

//        val mMessageReceiver = object : BroadcastReceiver() {
//            override fun onReceive(p0: Context?, intent: Intent?) {
//                val audio = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
//                binding.seekBarVolumeControl.progress = binding.seekBarVolumeControl.progress +
//                        intent!!.getIntExtra("volume", 0)
//
//                audio!!.setStreamVolume(
//                    AudioManager.STREAM_MUSIC,
//                    binding.seekBarVolumeControl.progress,
//                    0
//                )
//            }
//        }
//
//        context?.let {
//            LocalBroadcastManager.getInstance(it).registerReceiver(
//                mMessageReceiver,
//                IntentFilter("activity-says-hi")
//            )
//        }
//    }


    override fun onDestroy() {
        super.onDestroy()
        //api25 이하는 원래 해제를 따로 해줘야하지만, 지금 이앱에서는 해제시에 바인딩 된 음악도 해제되기 때문에 주석처리.
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.N_MR1)
            context?.unbindService(bindConnection)

        //테스트용 코드 정말 cancle 해줌. 음
//        ioScope.cancel()
    }

}
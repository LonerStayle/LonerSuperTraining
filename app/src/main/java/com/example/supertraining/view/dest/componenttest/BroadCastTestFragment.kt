package com.example.supertraining.view.dest.componenttest

import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.navigation.NavDeepLinkBuilder
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentBroadCastTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.toastShortShow

class BroadCastTestFragment :
    BaseFragment<FragmentBroadCastTestBinding>(R.layout.fragment_broad_cast_test) {

    private val alarmManager by lazy { requireContext().getSystemService(ALARM_SERVICE) as AlarmManager }
    //private var br:BroadcastReceiver? = null
    private val navDeepLink by lazy { NavDeepLinkBuilder(requireContext())
        .setGraph(R.navigation.navi)
        .setDestination(R.id.mainFragment)
        .setDestination(R.id.broadCastTestFragment)
        .createPendingIntent()}

    override fun FragmentBroadCastTestBinding.setDataBind() {
        broadCastTest = this@BroadCastTestFragment

//동적 브로드캐스트 리시버
//        val filter = IntentFilter()
        //액션 추가
//        filter.addAction("aaa")
//        filter.addAction("bbb")

//         br = object : BroadcastReceiver() {
//            override fun onReceive(p0: Context?, p1: Intent?) {

//            }
//
//        }
        //연결
//        requireActivity().registerReceiver(br,filter)
        //연결 해제
//        requireActivity().unregisterReceiver(br)
    }


    override fun FragmentBroadCastTestBinding.setClickListener() {
        setToggleButtonAlramTestClickListener()
        setToggleButtonPreiodAlramClcikListener()
    }


    fun setButtonClickListener(v: View) {
        Intent().also { intent ->
            //브로드캐스트 액션 커스텀
            //정적 브로드캐스트는  매니패스트에 같은 액션이 있어야함
            intent.action = requireActivity().packageName
            requireContext().sendBroadcast(intent)
            Log.d("packgeCheck", requireActivity().packageName)
        }
    }
   private fun setToggleButtonAlramTestClickListener() {

//    val intent = Intent(requireContext(), AlarmReceiver::class.java)  // 1
//    val pendingIntent = PendingIntent.getBroadcast(     // 2
//        requireContext(), AlarmReceiver.NOTIFICATION_ID, intent,
//        PendingIntent.FLAG_UPDATE_CURRENT
//    )


        binding.toggleButtonAlramTest.setOnCheckedChangeListener { _, isChecked ->

            val toastMessage = if (isChecked) {
                val triggerTime = (SystemClock.elapsedRealtime() + 3000)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //set은 부정확
                    //setExact는 정확 하지만 절전모드일때 발생안함
                    //set,setExact AndAllowWhileIdle는 절전 일때도 발생
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        triggerTime,
                        navDeepLink
                    )
                }
                "OneTime Alarm On"
            } else{
                alarmManager.cancel(navDeepLink)
                "OneTime Alarm Off"
            }
            Log.d("alramTest", toastMessage)
            requireContext().toastShortShow(toastMessage)

        }
    }

    private fun setToggleButtonPreiodAlramClcikListener(){
        binding.toggleButtonPreiodAlram.setOnCheckedChangeListener{_,isChecked->


            val toastMessage = if (isChecked) {
                val repeatInterval: Long = 60*1000
                val triggerTime = (SystemClock.elapsedRealtime()
                        + repeatInterval)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //setRepeating은 정확한 시간
                    //setIn~은 부정확한 시간
                    alarmManager.setRepeating(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        triggerTime,repeatInterval,
                        navDeepLink
                    )
                }
                "OneTime Alarm On"
            } else{
                alarmManager.cancel(navDeepLink)
                "OneTime Alarm Off"
            }
            Log.d("alramTest", toastMessage)
            requireContext().toastShortShow(toastMessage)

        }
    }

}
package com.example.supertraining.view.dest

import android.content.*
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.supertraining.R
import com.example.supertraining.Service.ServiceTest
import com.example.supertraining.databinding.FragmentServiceTestBinding
import com.example.supertraining.view.base.BaseFragment

class ServiceTestFragment() :
    BaseFragment<FragmentServiceTestBinding>(R.layout.fragment_service_test) {


    override fun FragmentServiceTestBinding.setDataBind() {
        setBroadCastReceiver()

    }

    override fun FragmentServiceTestBinding.setClickListener() {
        setButtonServiceStartClickListener()
        setButtonServiceBindStart()
    }



    private fun FragmentServiceTestBinding.setButtonServiceStartClickListener() {
        buttonServiceStart.setOnClickListener {
            requireContext().startService(Intent(requireContext(), ServiceTest::class.java))
        }
    }
    private fun FragmentServiceTestBinding.setButtonServiceBindStart() {
        buttonServiceBindStart.setOnClickListener {
            val bindServiceConnection = object : ServiceConnection {
                override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                }

                override fun onServiceDisconnected(p0: ComponentName?) {
                    Toast.makeText(
                        requireContext(),
                        "예기치 못한 사정으로바인드 연결이 끊겼습니다.",
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


}
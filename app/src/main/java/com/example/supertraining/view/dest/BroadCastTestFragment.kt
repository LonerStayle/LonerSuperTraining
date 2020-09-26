package com.example.supertraining.view.dest

import android.content.Intent
import android.util.Log
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentBroadCastTestBinding

import com.example.supertraining.view.base.BaseFragment

class BroadCastTestFragment :
    BaseFragment<FragmentBroadCastTestBinding>(R.layout.fragment_broad_cast_test) {

//private var br:BroadcastReceiver? = null

    override fun FragmentBroadCastTestBinding.setDataBind() {

//동적 브로드캐스트 리시버
//        val filter = IntentFilter()
        //액션 추가
//        filter.addAction("aaa")
//        filter.addAction("bbb")
//
//
//         br = object : BroadcastReceiver() {
//            override fun onReceive(p0: Context?, p1: Intent?) {
//                TODO("Not yet implemented")
//            }
//
//        }
        //연결
//        requireActivity().registerReceiver(br,filter)
        //연결 해제
//        requireActivity().unregisterReceiver(br)
    }


    override fun FragmentBroadCastTestBinding.setClickListener() {
        setButtonClickListener()
    }


    private fun FragmentBroadCastTestBinding.setButtonClickListener() {
        buttonBroadCastTestStart.setOnClickListener {
            Intent().also { intent ->
                //브로드캐스트 액션 커스텀
                //정적 브로드캐스트는  매니패스트에 같은 액션이 있어야함
                intent.action = requireActivity().packageName
                requireContext().sendBroadcast(intent)
                Log.d("packgeCheck", requireActivity().packageName)
            }
        }
    }

}
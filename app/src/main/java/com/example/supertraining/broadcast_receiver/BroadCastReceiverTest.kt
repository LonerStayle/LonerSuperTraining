package com.example.supertraining.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

//intent 에 해당 Reciver 넣어서 sendBroadCast 하면 해당 Reciver를 받을 수 있는 앱이 받고 onReceive 동작을 함
class BroadCastReceiverTest:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.action != context!!.packageName)
            return
        Toast.makeText(context, "리시버를 실행 합니다.", Toast.LENGTH_SHORT).show()


    }
}






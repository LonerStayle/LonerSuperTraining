package com.example.supertraining

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "d54e756dbcc3d4bf8fe94ba43490e0da")
    }
}
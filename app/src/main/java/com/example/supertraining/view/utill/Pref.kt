package com.example.supertraining.view.utill

import android.content.Context

class Pref private constructor(val context: Context){

    enum class Key{
        SET_VOLUME
    }
    companion object{
        private var instance:Pref? = null

        fun getInstance(context: Context):Pref =  instance?:Pref(context).also {
            instance = it
        }
    }

    private val pref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    var setVolume:Int
        get() = pref.getInt(Key.SET_VOLUME.name,0)
        set(value) {
            pref.edit().putInt(Key.SET_VOLUME.name, value).apply()
        }

}
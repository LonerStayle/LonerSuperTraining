package com.example.supertraining.view.pref

import android.content.Context

class PreferencesControl private constructor(val context: Context) {

    enum class Key {
        SET_NARRATION_SOUND_PATH,
        SET_START_NARRATIONS_SOUND_PATH,
        APP_FIRST_MUSIC_PLAY
    }

    companion object {
        private var instance: PreferencesControl? = null
        fun getInstance(context: Context): PreferencesControl =
            instance ?: PreferencesControl(context).also {
                instance = it
            }

    }

    private val pref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    var setNarrationSoundPath: String?
        get() = pref.getString(Key.SET_NARRATION_SOUND_PATH.name, null)
        set(value) {
            pref.edit().putString(Key.SET_NARRATION_SOUND_PATH.name, value).apply()
        }

    var setStartNarrationsSoundPath: String?
        get() = pref.getString(Key.SET_START_NARRATIONS_SOUND_PATH.name, null)
        set(value) {
            pref.edit().putString(Key.SET_START_NARRATIONS_SOUND_PATH.name, value).apply()
        }

    var appFirstMusicPlay:Boolean
        get() = pref.getBoolean(Key.APP_FIRST_MUSIC_PLAY.name, false)
        set(value) {
            pref.edit().putBoolean(Key.APP_FIRST_MUSIC_PLAY.name, value).apply()
        }


}

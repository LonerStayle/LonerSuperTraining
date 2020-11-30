package com.example.supertraining.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)

        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode)
            Log.e("GeofenceBR", errorMessage)
            return
        }
        val geofenceTransition = geofencingEvent.geofenceTransition    // 발생 이벤트 타입

        if (geofenceTransition != Geofence.GEOFENCE_TRANSITION_ENTER)
            return

            val triggeringGeofences = geofencingEvent.triggeringGeofences
            val transitionMsg =  "Enter"

            triggeringGeofences.forEach {
                Toast.makeText(context, "${it.requestId} - $transitionMsg", Toast.LENGTH_LONG).show()
            }

    }
}
package com.example.supertraining.view.utill

import com.minew.beacon.MinewBeacon
import kotlin.math.pow

fun MinewBeacon.getDistance(txPower:Float, rssi:Float):Float {
    if (rssi == 0.0f) {
        return -1.0f; // if we cannot determine accuracy, return -1.
    }

    val ratio = rssi * 1.0f / txPower;
    return if (ratio < 1.0f) {
        ratio.pow(10.0f)
    } else {
        (0.89976f) * ratio.pow(7.7095f) + 0.111f
    }
}
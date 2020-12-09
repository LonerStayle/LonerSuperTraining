package com.example.supertraining.view.dest

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.supertraining.R
import com.example.supertraining.service.BackgroundSoundService
import com.example.supertraining.databinding.FragmentGeofencingTestBinding
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.Contents
import com.example.supertraining.view.utill.tedPermissionCheck
import com.google.android.gms.location.*
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.util.FusedLocationSource


class GeoFencingTestFragment :
    BaseFragment<FragmentGeofencingTestBinding>(R.layout.fragment_geofencing_test) {
    companion object {
        const val MY_PERMISSIONS_REQ_LOCATION = 1500
    }

    val geofenceList: MutableList<Geofence> by lazy {

        mutableListOf(
            getGeofenceEnter("현대백화점", Pair(37.5085864, 127.0601149)),
            getGeofenceEnter("삼성역", Pair(37.5094518, 127.063603))
        )
    }

    private val geofencingClient: GeofencingClient by lazy {
        LocationServices.getGeofencingClient(requireContext())
    }

    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, BackgroundSoundService::class.java).also {
            it.putExtra(
                "music", Contents.IMAGE_URL_DEFAULT_FILE_PATH +
                        resources.getIdentifier(
                            "music_sample_background",
                            "raw",
                            requireContext().packageName
                        ).toString()
            )
        }
        PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    }

    override fun FragmentGeofencingTestBinding.setDataBind() {

        setPermission {

            val mapFragment = setupMapFragment()

            mapFragment?.getMapAsync {
                setLocationSource(it)
            }

            if (ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                return@setPermission
            }
            geofencingClient.addGeofences(
                getGeofencingRequest(geofenceList),
                geofencePendingIntent
            )
        }

    }

    private fun setupMapFragment(): MapFragment? {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                childFragmentManager.beginTransaction().add(R.id.mapFragment, it).commit()
            }
        return mapFragment
    }

    private fun setLocationSource(it: NaverMap) {
        val fusedLocationSource = FusedLocationSource(
            this@GeoFencingTestFragment,
            MY_PERMISSIONS_REQ_LOCATION
        )
        it.locationSource = fusedLocationSource
        it.locationTrackingMode = LocationTrackingMode.Follow
    }

    private fun setPermission(startGeofences: () -> Unit) {
        context?.tedPermissionCheck {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                MY_PERMISSIONS_REQ_LOCATION
            )

            startGeofences()
        }
    }

    private fun getGeofenceEnter(reqId: String, geo: Pair<Double, Double>, radius: Float = 100f) =
        Geofence.Builder()
            .setRequestId(reqId)
            .setCircularRegion(geo.first, geo.second, radius) // 반경 조절
            .setExpirationDuration(Geofence.NEVER_EXPIRE) // 만료시간
            .setTransitionTypes(
                Geofence.GEOFENCE_TRANSITION_ENTER
            ) // 어떤 상황에 반응할지.
            .build()

    private fun getGeofencingRequest(list: List<Geofence>) =
        GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            // Geofence 이벤트는 진입시 부터 처리할 때
            addGeofences(list)
        }.build()


}
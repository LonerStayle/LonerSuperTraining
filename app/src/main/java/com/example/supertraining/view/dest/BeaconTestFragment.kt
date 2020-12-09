package com.example.supertraining.view.dest

import android.bluetooth.BluetoothAdapter
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentBeaconTestBinding
import com.example.supertraining.service.BeaconService
import com.example.supertraining.view.adapter.RecyclerViewBeaconTestAdapter
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.toastLongShow
import com.minew.beacon.BluetoothState
import com.minew.beacon.MinewBeacon
import com.minew.beacon.MinewBeaconManager
import com.minew.beacon.MinewBeaconManagerListener
import kotlinx.coroutines.*
import org.altbeacon.beacon.*
import java.util.*


class BeaconTestFragment : BaseFragment<FragmentBeaconTestBinding>(R.layout.fragment_beacon_test),
    BeaconConsumer {
    companion object {
        const val IBEACON_LAYOUT = "m:2-3=0215,i:4-19,:20-21,i:22-23,p:24-24";
        const val REQUEST_ENABLE_BT = 4444
//        m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25
    }

    private var beaconManager: BeaconManager? = null
    private lateinit var beaconService: BeaconService
    private lateinit var bindConnection: ServiceConnection


    override fun FragmentBeaconTestBinding.setDataBind() {
        setBindConnection()
        startBindService()
        setButtonIBeaconSdkClickListener()
    }

    private fun startBindService() {
        Intent(context, BeaconService::class.java).let {
            context?.bindService(
                it,
                bindConnection,
                Context.BIND_AUTO_CREATE
            )
        }
    }


    fun setButtonAltBeaconLibraryClickListener(v: View) {
        beaconManager = BeaconManager.getInstanceForApplication(requireContext())

        beaconManager!!.beaconParsers.add(BeaconParser().setBeaconLayout(IBEACON_LAYOUT));
        beaconManager?.bind(this)
    }


    private fun setBindConnection() {
        bindConnection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
                val binder = service as BeaconService.LocalBinder
                beaconService = binder.getService()
            }

            override fun onServiceDisconnected(p0: ComponentName?) {}
        }
    }

    private fun setButtonIBeaconSdkClickListener() {
        binding.buttonIBeaconSdk.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                beaconService.apply {

                    mMinewBeaconManager = MinewBeaconManager.getInstance(requireContext())
                        showBLEDialog()


                    setInitService()
                    mMinewBeaconManager!!.setDeviceManagerDelegateListener(object :
                        MinewBeaconManagerListener {

                        override fun onAppearBeacons(minewBeacons: MutableList<MinewBeacon>?) {
                            context?.toastLongShow("새로운 스캔을 했을때 호출")
                        }

                        override fun onDisappearBeacons(minewBeacons: MutableList<MinewBeacon>?) {
                            context?.toastLongShow("10초이상 스캔이 호출되지 않으면 이 메서드 호출")
                        }

                        override fun onRangeBeacons(minewBeacons: MutableList<MinewBeacon>?) {
                            Log.d("salusTest_1second_scan","1초마다 스캔중 일때 호출")

                            if (!isResumed)
                                return
                            Collections.sort(minewBeacons!!,comp)
                            binding.recyclerViewIBeaconTest.adapter =
                                RecyclerViewBeaconTestAdapter().apply {
                                    beaconList = minewBeacons
                                }

                        }

                        override fun onUpdateState(state: BluetoothState?) {
                            when (state) {
                                BluetoothState.BluetoothStatePowerOn ->
                                    context?.toastLongShow("블루투스 기능이 ON 됬습니다")
                                BluetoothState.BluetoothStatePowerOff ->
                                    context?.toastLongShow("블루투스 기능이 OFF 됬습니다")
                                else -> return
                            }
                        }
                    })

                }

            }
        }
    }


    private fun showBLEDialog() {
        val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT) {
            beaconService.mMinewBeaconManager!!.startScan()
        }
    }


    override fun onBeaconServiceConnect() {
        beaconManager!!.removeAllMonitorNotifiers()
        beaconManager!!.addMonitorNotifier(object : MonitorNotifier {
            override fun didEnterRegion(region: Region?) {
                context!!.toastLongShow("방금 비콘을 처음 봤어요!")
                region!!.bluetoothAddress
            }

            override fun didExitRegion(region: Region?) {
                context!!.toastLongShow("더 이상 비콘이 보이지 않습니다.")
            }

            override fun didDetermineStateForRegion(state: Int, region: Region?) {
                //전환 할때
                context!!.toastLongShow("방금 비콘을 보거나 보지 못하도록 전환했습니다: $state")
            }

        })

        beaconManager!!.addRangeNotifier { beacons, _ ->

            if (beacons.isNotEmpty()) {
                Log.d(
                    "TAG2",
                    ":::::The first beacon I see is about " + beacons.iterator().next()
                        .distance.toString() + " meters away.:::::"
                )
                Log.d(
                    "TAG2",
                    ":::::This :: U U I D :: of beacon   :  " + beacons.iterator().next()
                        .id1.toString() + ":::::"
                )
                Log.d(
                    "TAG2",
                    ":::::This ::M a j o r:: of beacon   :  " + beacons.iterator().next()
                        .id2.toString() + ":::::"
                )
                Log.d(
                    "TAG2",
                    ":::::This ::M i n o r:: of beacon   :  " + beacons.iterator().next()
                        .id3.toString() + ":::::"
                )
            }
        }

        try {
            beaconManager!!.startMonitoringBeaconsInRegion(
                Region(
                    "myMonitoringUniqueId",
                    null,
                    null,
                    null
                )
            )
        } catch (e: RemoteException) {
        }
    }

    override fun getApplicationContext() = requireContext()
    override fun unbindService(serviceConnection: ServiceConnection?) {}
    override fun bindService(intent: Intent?, p1: ServiceConnection?, p2: Int): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        beaconManager?.unbind(this)

    }


}
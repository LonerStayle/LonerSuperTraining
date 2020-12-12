package com.example.supertraining.view.dest

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.content.*
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.core.app.NotificationCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.estimote.proximity_sdk.api.EstimoteCloudCredentials
import com.estimote.proximity_sdk.api.ProximityObserver
import com.estimote.proximity_sdk.api.ProximityObserverBuilder
import com.estimote.proximity_sdk.api.ProximityZoneBuilder
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentBeaconTestBinding
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.service.BeaconService
import com.example.supertraining.service.ServiceTest
import com.example.supertraining.view.activity.MainActivity
import com.example.supertraining.view.adapter.RecyclerViewBeaconTestAdapter
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.toastLongShow
import com.example.supertraining.viewmodel.TestViewModel
import com.example.supertraining.viewmodel.factory.ViewModelFactory
import com.minew.beacon.*
import kotlinx.android.synthetic.main.fragment_beacon_test.*
import kotlinx.coroutines.*
import org.altbeacon.beacon.*
import java.util.*


class BeaconTestFragment : BaseFragment<FragmentBeaconTestBinding>(R.layout.fragment_beacon_test) {
    private var bluetoothAdapter: BluetoothAdapter? = null

    companion object {
        const val IBEACON_LAYOUT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"

//        "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"
//        "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"
    }

    private var beaconManager: BeaconManager? = null

    private lateinit var beaconService: BeaconService
    private lateinit var bindConnection: ServiceConnection
    private lateinit var observationHandler: ProximityObserver.Handler
    private lateinit var consumer: BeaconConsumer

    override fun FragmentBeaconTestBinding.setDataBind() {
        this.beaconTestFragment = this@BeaconTestFragment
        setBluetoothAdapter()
        setBindConnection()
        startBindService()
    }

    private fun setBluetoothAdapter() {
        bluetoothAdapter ?: BluetoothAdapter.getDefaultAdapter().also {
            bluetoothAdapter = it
        }
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


    private fun setConsumer() {
        consumer = object : BeaconConsumer {
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
        }
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


//    private fun showBLEDialog() {
//        if (!viewModel.beaconBackEventCheck) {
//            val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            startActivityForResult(enableIntent, REQUEST_ENABLE_BT)
//        } else {
//            findNavController().popBackStack()
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_ENABLE_BT) {
//            if (requestCode == Activity.RESULT_OK) {
//                bluetoothAdapter!!.enable()
//            }
//            if (!bluetoothAdapter!!.isEnabled) {
//                context?.toastLongShow("여기다 TTS 넣을거임 블루투스를 꺼주지 말아주세요 While문 ")
//            }
//        }
//    }

    fun setButtonEstimoteUseClickListener(v: View) {

        // KOTLIN
        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            requireContext(),
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(requireContext(), ServiceTest.SERVICE_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_dialer)
            .setContentIntent(pendingIntent)
            .setContentTitle(" Beacon scan ")
            .setContentText(" Scan is running ... ")
            .setPriority(Notification.PRIORITY_HIGH)
            .build()

        val cloudCredentials = EstimoteCloudCredentials(
            resources.getString(R.string.estimote_app_id),
            resources.getString(R.string.estimote_app_token)
        )
        val proximityObserver =
            ProximityObserverBuilder(requireContext().applicationContext, cloudCredentials)
                .withBalancedPowerMode()
                .onError { /* handle errors here */ }
                .withScannerInForegroundService(builder)
                .build()

        // Kotlin
        val venueZone = ProximityZoneBuilder()
            .forTag("venue")
            .inFarRange()
            .onEnter { Log.d("asdasd", "입장 했을때 ${it.deviceId}") }
            .onExit { Log.d("asdasd", "비콘에서 나갔을때") }
            .onContextChange {}
            .build()

        observationHandler = proximityObserver.startObserving(venueZone)

    }


    fun setButtonIBeaconSdkClickListener(v: View) {

        CoroutineScope(Dispatchers.Main).launch {
            beaconService.apply {

                mMinewBeaconManager = MinewBeaconManager.getInstance(requireContext())
                setInitService()

                mMinewBeaconManager!!.startScan()

                mMinewBeaconManager!!.setDeviceManagerDelegateListener(object :
                    MinewBeaconManagerListener {

                    override fun onAppearBeacons(minewBeacons: MutableList<MinewBeacon>?) {
                        context?.toastLongShow("새로운 스캔을 했을때 호출")
                    }

                    override fun onDisappearBeacons(minewBeacons: MutableList<MinewBeacon>?) {
                        context?.toastLongShow("10초이상 스캔이 호출되지 않으면 이 메서드 호출")
                    }

                    override fun onRangeBeacons(minewBeacons: MutableList<MinewBeacon>?) {
                        Log.d("salusTest_1second_scan", "1초마다 스캔중 일때 호출")

                        val busList =
                            minewBeacons?.filter { it.getBeaconValue(BeaconValueIndex.MinewBeaconValueIndex_RSSI).intValue > -470 }


                        if (!isResumed)
                            return
                        Collections.sort(minewBeacons!!, comp)
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


    fun setButtonAltBeaconLibraryClickListener(v: View) {
        beaconManager = BeaconManager.getInstanceForApplication(requireContext())
        beaconManager!!.beaconParsers.add(BeaconParser().setBeaconLayout(IBEACON_LAYOUT));
        setConsumer()
        beaconManager?.bind(consumer)

    }

    private fun enableDisableBT() {
        if (bluetoothAdapter == null)
            Log.d("blueToothNullCheck", "잡힌 블루투스가 하나도 없습니다.")

        if (!bluetoothAdapter!!.isEnabled) {
            Log.d("blueToothNullCheck", "블루투스가 활성화를 시도합니다.")
            val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivity(enableBTIntent)

            val bTIntent = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
            context?.registerReceiver(mBroadCastReceiver, bTIntent)
        }
    }

    private val mBroadCastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {

                    BluetoothAdapter.STATE_ON ->
                        Log.d(
                            "blueToothChangeCheck",
                            "onReceive State Off"
                        )

                    BluetoothAdapter.STATE_OFF ->
                        Log.d(
                            "blueToothChangeCheck",
                            "이곳에서 TTS처리"
                        )

                    BluetoothAdapter.STATE_TURNING_ON ->
                        Log.d(
                            "blueToothChangeCheck",
                            "onReceive turning on"
                        )

                    BluetoothAdapter.STATE_TURNING_OFF -> {
                        enableDisableBT()
                        Log.d(
                            "blueToothChangeCheck",
                            "이곳에서 TTS처리"
                        )
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        enableDisableBT()

    }

    override fun onDestroy() {
        super.onDestroy()
        beaconManager?.unbind(consumer) ?: return

    }


}
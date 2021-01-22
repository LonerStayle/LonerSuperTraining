package com.example.supertraining.view.dest

import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentMainBinding
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.db.locale_db.entity.RoomEntityTest
import com.example.supertraining.view.adapter.RecyclerViewMainAdapter
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.DialogSimple
import com.example.supertraining.view.utill.toastShortShow
import com.example.supertraining.viewmodel.TestViewModel
import com.example.supertraining.viewmodel.factory.ViewModelFactory
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.manager.Lifecycle
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel by viewModels<TestViewModel> {
        val testDatabase = TestDataBase.getInstance(requireContext())
        val factory = ViewModelFactory(testDatabase.dataSource)
        factory
    }

    override fun FragmentMainBinding.setDataBind() {
        main = this@MainFragment
        mainViewModelTest()
        Log.d("lifeCycleTest", "onCreateView")

        lifecycleScopeTest()
    }

    private fun lifecycleScopeTest() {


            lifecycleScope.launchWhenCreated {
                Log.d("lifecycleScope", "Created")
            }
            lifecycleScope.launchWhenStarted {
                Log.d("lifecycleScope", "Started")
            }
            lifecycleScope.launchWhenResumed {
                Log.d("lifecycleScope", "Resumed")
            }

    }

    private fun FragmentMainBinding.mainViewModelTest() {
        mainViewModel.insert(
            RoomEntityTest(
                text = resources.getStringArray(R.array.testList).toList()
            )
        )
        mainViewModel.testList.observe(viewLifecycleOwner, {
            recyclerview.adapter = RecyclerViewMainAdapter(it)
        })
    }

    fun setButtonGoToTheBroadCastTextClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_broadCastTestFragment)
    }

    fun setButtonGoToTheServiceTestClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_serviceTestFragment)
    }

    fun setButtonContentProviderClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_contentProviderTest)
    }

    fun setButtonGoToTheNaviClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_naviTestFragment)
    }

    fun setButtonGotoSearchViewTestClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_searchViewTestFragment)
    }

    fun setButtonNetworkTestClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_retroFitTestFragment)
    }

    fun setButtonAccessibilitySettingTestClickListener(v: View) {
        requireContext().startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    fun setButtonGoToTheGeoFencingClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_geoFencingTestFragment)
    }

    fun setButtonGoToTheBeaconTestClickListener(v: View) {
        findNavController().navigate(R.id.action_mainFragment_to_beaconTestFragment)
    }

    fun setButtonDialogStartClickListener(v: View) {
        DialogSimple.show(
            requireContext(),
            "asdasdadasd",
            "asdaa\na\na\na\na\na\na\na\na\na\na\na\n\n\n\n\n\n\n\n\n\n\n\n\n",
            "에",
            { context?.toastShortShow("뭠마") },
            "ㅁㄴㅇ",
            { return@show }
        )
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycleTest", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeCycleTest", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifeCycleTest", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifeCycleTest", "onDestroyView")
    }


}
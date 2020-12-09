package com.example.supertraining.view.dest

import android.content.Intent
import android.provider.Settings
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentMainBinding
import com.example.supertraining.db.locale_db.TestDataBase
import com.example.supertraining.db.locale_db.entity.RoomEntityTest
import com.example.supertraining.view.adapter.RecyclerViewMainAdapter
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.viewmodel.TestViewModel
import com.example.supertraining.viewmodel.factory.ViewModelFactory

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val mainViewModel by viewModels<TestViewModel> {
        val testDatabase = TestDataBase.getInstance(requireContext())
        val factory = ViewModelFactory(testDatabase.dataSource)
        factory
    }

    override fun FragmentMainBinding.setDataBind() {
        main = this@MainFragment
        mainViewModelTest()
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

    fun setButtonGotoSearchViewTestClickListener(v: View){
        findNavController().navigate(R.id.action_mainFragment_to_searchViewTestFragment)
    }

    fun setButtonNetworkTestClickListener(v: View){
        findNavController().navigate(R.id.action_mainFragment_to_retroFitTestFragment)
    }

    fun setButtonAccessibilitySettingTestClickListener(v: View){
        requireContext().startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).also {
          it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    fun setButtonGoToTheGeoFencingClickListener(v:View){
        findNavController().navigate(R.id.action_mainFragment_to_geoFencingTestFragment)
    }

    fun setButtonGoToTheBeaconTestClickListener(v:View){
        findNavController().navigate(R.id.action_mainFragment_to_beaconTestFragment)
    }
}
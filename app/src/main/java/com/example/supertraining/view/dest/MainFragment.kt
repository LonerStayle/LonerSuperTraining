package com.example.supertraining.view.dest

import android.content.Intent
import android.provider.Settings
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.supertraining.R
import com.example.supertraining.databinding.FragmentMainBinding
import com.example.supertraining.db.locale_db.entity.RoomEntityTest
import com.example.supertraining.view.adapter.RecyclerViewMainAdapter
import com.example.supertraining.view.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

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

}